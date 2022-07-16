package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityMobileVerifyBinding;
import com.sefueemisor.retrofit.ApiClient;
import com.sefueemisor.retrofit.Constant;
import com.sefueemisor.retrofit.EmisorInterface;
import com.sefueemisor.utils.DataManager;
import com.sefueemisor.utils.NetworkAvailablity;
import com.sefueemisor.utils.SessionManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MobilrVerifyAct extends AppCompatActivity {
    public String TAG = "MobilrVerifyAct";
    ActivityMobileVerifyBinding binding;
    EmisorInterface apiInterface;
    String email = "", userName = "", password = "", phone = "", countryCode = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mobile_verify);
        initViews();
    }

    private void initViews() {

        if (getIntent() != null) {
            email = getIntent().getStringExtra("email");
            userName = getIntent().getStringExtra("userName");
            password = getIntent().getStringExtra("password");
            phone = getIntent().getStringExtra("phone");
            countryCode = getIntent().getStringExtra("countryCode");
        }

        binding.et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.et2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.et3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.et4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.btnCountinue.setOnClickListener(v -> validation());
    }

    private void validation() {
        if (TextUtils.isEmpty(binding.et1.getText().toString().trim())) {
            Toast.makeText(MobilrVerifyAct.this, getString(R.string.invalid_otp), Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(binding.et2.getText().toString().trim())) {
            Toast.makeText(MobilrVerifyAct.this, getString(R.string.invalid_otp), Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(binding.et3.getText().toString().trim())) {
            Toast.makeText(MobilrVerifyAct.this, getString(R.string.invalid_otp), Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(binding.et4.getText().toString().trim())) {
            Toast.makeText(MobilrVerifyAct.this, getString(R.string.invalid_otp), Toast.LENGTH_SHORT).show();
        } else {

            String otpFull = binding.et1.getText().toString().trim() +
                    binding.et2.getText().toString().trim() +
                    binding.et3.getText().toString().trim() +
                    binding.et4.getText().toString().trim();
            if (!otpFull.equals("0000")) {
                Toast.makeText(MobilrVerifyAct.this, getString(R.string.invalid_otp), Toast.LENGTH_SHORT).show();
            } else {
                if (NetworkAvailablity.checkNetworkStatus(MobilrVerifyAct.this)) signupUser();
                else
                    Toast.makeText(MobilrVerifyAct.this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void signupUser() {

        DataManager.getInstance().showProgressMessage(MobilrVerifyAct.this, getString(R.string.please_wait));
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("user_name", userName);
        map.put("password", password);
        map.put("mobile", phone);
        map.put("country_code", "+" + countryCode);
        map.put("register_id", "dgkjdgkgngd");
        map.put("lat", "444");
        map.put("lon", "4335");
        Log.e(TAG, "Signup Request = " + map);
        apiInterface = ApiClient.getClient().create(EmisorInterface.class);
        Call<ResponseBody> call = apiInterface.signupUser(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    String responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    Log.e(TAG, "Signup Response = " + responseString);

                    if (jsonObject.getString("status").equals("1")) {
                        // modelLogin = new Gson().fromJson(responseString, ModelLogin.class);
                        SessionManager.writeString(MobilrVerifyAct.this, Constant.USER_INFO, responseString);
                        startActivity(new Intent(MobilrVerifyAct.this, PasswordAct.class));
                        finish();

                    } else {
                        Toast.makeText(MobilrVerifyAct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(MobilrVerifyAct.this, "Exception = " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Exception", "Exception = " + e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                DataManager.getInstance().hideProgressMessage();
                call.cancel();
                Log.e("Exception", "Throwable = " + t.getMessage());
            }

        });
    }


}
