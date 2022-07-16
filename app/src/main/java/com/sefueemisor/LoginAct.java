package com.sefueemisor;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityLoginBinding;
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

public class LoginAct extends AppCompatActivity {
    public String TAG = "LoginAct";
    EmisorInterface apiInterface;
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        initViews();
    }

    private void initViews() {
         binding.btnLogin.setOnClickListener(v -> validation());

        binding.tvForgotPass.setOnClickListener(v -> startActivity(new Intent(LoginAct.this,ForgotPasswordAct.class)));

    }

    private void validation() {
        if(binding.etEmail.getText().toString().equals("")){
            Toast.makeText(LoginAct.this, getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
        }
        else if(!binding.etEmail.getText().toString().matches(Constant.emailPattern)){
            Toast.makeText(LoginAct.this, getString(R.string.wrong_email), Toast.LENGTH_SHORT).show();
        }
        else if(binding.etPassword.getText().toString().equals("")){
            Toast.makeText(LoginAct.this, getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
        }

        else {
            if (NetworkAvailablity.checkNetworkStatus(LoginAct.this)) loginUser();
            else
                Toast.makeText(LoginAct.this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();
        }

    }


    public void loginUser() {
        DataManager.getInstance().showProgressMessage(LoginAct.this, getString(R.string.please_wait));
        Map<String, String> map = new HashMap<>();
        map.put("email", binding.etEmail.getText().toString());
        map.put("password", binding.etPassword.getText().toString());
        map.put("register_id", "dgkjdgkgngd");
        Log.e(TAG, "Login Request = " + map);
        apiInterface = ApiClient.getClient().create(EmisorInterface.class);
        Call<ResponseBody> call = apiInterface.userLogin(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    String responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    Log.e(TAG, "Login Response = " + responseString);

                    if (jsonObject.getString("status").equals("1")) {
                        // modelLogin = new Gson().fromJson(responseString, ModelLogin.class);
                        SessionManager.writeString(LoginAct.this, Constant.USER_INFO, responseString);
                        startActivity(new Intent(LoginAct.this, HomeAct.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();

                    } else {
                        Toast.makeText(LoginAct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(LoginAct.this, "Exception = " + e.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn){

            if(binding.etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.eye);

                //Show Password
                binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.blind);

                //Hide Password
                binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }



}
