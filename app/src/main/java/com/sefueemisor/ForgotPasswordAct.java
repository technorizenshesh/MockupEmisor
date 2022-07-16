package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityForgotPassBinding;
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

public class ForgotPasswordAct extends AppCompatActivity {
    public String TAG = "ForgotPasswordAct";
    ActivityForgotPassBinding binding;
    EmisorInterface apiInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_pass);
        initViews();
    }


    private void initViews() {
        binding.btnSubmit.setOnClickListener(v -> validation());
    }

    private void validation() {
        if(binding.etEmail.getText().toString().equals("")){
            Toast.makeText(ForgotPasswordAct.this, getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
        }
        else if(!binding.etEmail.getText().toString().matches(Constant.emailPattern)){
            Toast.makeText(ForgotPasswordAct.this, getString(R.string.wrong_email), Toast.LENGTH_SHORT).show();
        }


        else {
            if (NetworkAvailablity.checkNetworkStatus(ForgotPasswordAct.this)) forgotPassUser();
            else
                Toast.makeText(ForgotPasswordAct.this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();
        }

    }


    public void forgotPassUser() {
        DataManager.getInstance().showProgressMessage(ForgotPasswordAct.this, getString(R.string.please_wait));
        Map<String, String> map = new HashMap<>();
        map.put("email", binding.etEmail.getText().toString());
        Log.e(TAG, "Forgot Pass Request = " + map);
        apiInterface = ApiClient.getClient().create(EmisorInterface.class);
        Call<ResponseBody> call = apiInterface.userForgotPass(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    String responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    Log.e(TAG, "Forgot Pass Response = " + responseString);
                    if (jsonObject.getString("status").equals("1")) {
                        // modelLogin = new Gson().fromJson(responseString, ModelLogin.class);
                        Toast.makeText(ForgotPasswordAct.this,getString(R.string.please_check_your_email),Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(ForgotPasswordAct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(ForgotPasswordAct.this, "Exception = " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
