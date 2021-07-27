package com.anorlddroid.smartloan.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anorlddroid.smartloan.MainActivity;
import com.anorlddroid.smartloan.R;
import com.anorlddroid.smartloan.database.UserDatabase;
import com.anorlddroid.smartloan.database.UserEntity;
import com.anorlddroid.smartloan.model.AccessToken;
import com.anorlddroid.smartloan.model.STKPush;
import com.anorlddroid.smartloan.services.DarajaApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.anorlddroid.smartloan.Constants.BUSINESS_SHORT_CODE;
import static com.anorlddroid.smartloan.Constants.CALLBACKURL;
import static com.anorlddroid.smartloan.Constants.PARTYB;
import static com.anorlddroid.smartloan.Constants.PASSKEY;
import static com.anorlddroid.smartloan.Constants.TRANSACTION_TYPE;

public class RegistrationPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDatabase userDatabase;
    private DarajaApiClient mApiClient;
    private ProgressDialog mProgressDialog;
    Button mPay ;
    Button mClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_payment);
        ButterKnife.bind(this);
        mPay = findViewById(R.id.click_to_pay);
        mClose = findViewById(R.id.close);


        mProgressDialog = new ProgressDialog(this);
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug(true); //Set True to enable logging, false to disable.

        TextView account = findViewById(R.id.account);
        userDatabase = UserDatabase.Companion.getUserDatabase(getApplicationContext());
        assert userDatabase != null;
        List<UserEntity> myPhoneNumber = Objects.requireNonNull(userDatabase.userDao()).getPhoneNumber();
        for (UserEntity number : myPhoneNumber) {
            if (number.getPhoneNumber() != null) {
                account.setText(number.getPhoneNumber());
            }
        }
        mPay.setOnClickListener(this);
        mClose.setOnClickListener(this);

        getAccessToken();

    }

    public void getAccessToken() {
        mApiClient.setGetAccessToken(true);
        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {

                if (response.isSuccessful()) {
                    mApiClient.setAuthToken(response.body().accessToken);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View view) {

        if (view == mPay) {
            userDatabase = UserDatabase.Companion.getUserDatabase(getApplicationContext());
            assert userDatabase != null;
            List<UserEntity> myPhoneNumber = Objects.requireNonNull(userDatabase.userDao()).getPhoneNumber();
            for (UserEntity number : myPhoneNumber) {
                if (number.getPhoneNumber() != null) {
                    String phone_number = number.getPhoneNumber();
                    String amount = "250";
                    performSTKPush(phone_number, amount);
                    break;
                }
            }
        }else if (view == mClose){
            finish();
            System.exit(0);
        }
    }


    public void performSTKPush(String phone_number, String amount) {
        mProgressDialog.setMessage("Processing your request");
        mProgressDialog.setTitle("Please Wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        String timestamp = com.anorlddroid.smartloan.Utils.getTimestamp();
        STKPush stkPush = new STKPush(
                BUSINESS_SHORT_CODE,
                com.anorlddroid.smartloan.Utils.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                String.valueOf(amount),
                com.anorlddroid.smartloan.Utils.sanitizePhoneNumber(phone_number),
                PARTYB,
                com.anorlddroid.smartloan.Utils.sanitizePhoneNumber(phone_number),
                CALLBACKURL,
                "SmartLoan Ltd", //Account reference
                "SmartLoan STK PUSH by TDBSoft"  //Transaction description
        );

        mApiClient.setGetAccessToken(false);

        //Sending the data to the Mpesa API, remember to remove the logging when in production.
        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<STKPush>() {
            @Override
            public void onResponse(@NonNull Call<STKPush> call, @NonNull Response<STKPush> response) {
                mProgressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        Timber.d("post submitted to API. %s", response.body());
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    UserEntity userEntity = new UserEntity();
                                    userEntity.setPaymentStatus("Active");
                                    Objects.requireNonNull(userDatabase.userDao()).paymentStatus(userEntity);
                                }
                            });
                        Intent i = new Intent(RegistrationPaymentActivity.this, SignInActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        Timber.e("Response %s", response.errorBody().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPush> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Timber.e(t);
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
