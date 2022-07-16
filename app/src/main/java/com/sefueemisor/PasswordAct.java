package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityPasswordBinding;
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


public class PasswordAct extends AppCompatActivity {
    public String TAG = "PasswordAct";
    ActivityPasswordBinding binding;
    EmisorInterface apiInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_password);
        initViews();
    }

    private void initViews() {
        binding.btnCountinue.setOnClickListener(v -> {
            if(binding.edPassword.getText().toString().equals(""))
                Toast.makeText(PasswordAct.this, getText(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
              else {
                  if(NetworkAvailablity.checkNetworkStatus(PasswordAct.this)) chkPassword();
                  else Toast.makeText(PasswordAct.this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void chkPassword() {
        DataManager.getInstance().showProgressMessage(PasswordAct.this, getString(R.string.please_wait));
        Map<String, String> map = new HashMap<>();
        map.put("password",binding.edPassword.getText().toString());
        map.put("user_id",DataManager.getInstance().getUserData(PasswordAct.this).getResult().getId()+"");
        Log.e(TAG, "Password Chk Request = " + map);
        apiInterface = ApiClient.getClient().create(EmisorInterface.class);
        Call<ResponseBody> call = apiInterface.chkPassUser(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    String responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    Log.e(TAG, "Password Chk Response = " + responseString);
                    if (jsonObject.getString("status").equals("1")) {
                        // modelLogin = new Gson().fromJson(responseString, ModelLogin.class);
                        SessionManager.writeString(PasswordAct.this, Constant.USER_INFO, responseString);
                        startActivity(new Intent(PasswordAct.this,HomeAct.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();

                    } else {
                        Toast.makeText(PasswordAct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(PasswordAct.this, "Exception = " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
