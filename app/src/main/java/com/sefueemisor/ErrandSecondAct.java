package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.sefueemisor.adapter.DocTypeAdapter;
import com.sefueemisor.databinding.ActivityErrandSecondBinding;
import com.sefueemisor.model.DocModel;
import com.sefueemisor.retrofit.ApiClient;
import com.sefueemisor.retrofit.Constant;
import com.sefueemisor.retrofit.EmisorInterface;
import com.sefueemisor.utils.DataManager;
import com.sefueemisor.utils.NetworkAvailablity;
import com.sefueemisor.utils.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ErrandSecondAct extends AppCompatActivity {
    public String TAG = "ErrandSecondAct";
    ActivityErrandSecondBinding binding;
    EmisorInterface apiInterface;
    ArrayList<DocModel.Result>arrayList;
    DocTypeAdapter typeAdapter;
    String docTypeId ="";

    public static TextView etAddress,etCity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = ApiClient.getClient().create(EmisorInterface.class);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_errand_second);
      initViews();
    }

    private void initViews() {

        etAddress = binding.etAddress;
        etCity = binding.etCity;

        arrayList = new ArrayList<>();

        typeAdapter = new DocTypeAdapter(ErrandSecondAct.this,arrayList);
        binding.spinnerDocType.setAdapter(typeAdapter);

        binding.ivBack.setOnClickListener(v -> finish());



      binding.spinnerDocType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             docTypeId = arrayList.get(position).getId();
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });

      binding.btnNext.setOnClickListener(v -> validation());


        binding.etAddress.setOnClickListener(v -> startActivity(new Intent(ErrandSecondAct.this,PinAddressAct.class)));



        if(NetworkAvailablity.checkNetworkStatus(ErrandSecondAct.this)) getAllDocType();
      else Toast.makeText(ErrandSecondAct.this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();


    }

    private void validation() {
        if(binding.etName.getText().toString().equals("")){
            binding.etName.setError(getString(R.string.required));
            binding.etName.setFocusable(true);
        }
       else if(binding.etSurname.getText().toString().equals("")){
            binding.etSurname.setError(getString(R.string.required));
            binding.etSurname.setFocusable(true);
        }

        else if(docTypeId.equals("")){
            Toast.makeText(ErrandSecondAct.this, getString(R.string.please_select_document_type), Toast.LENGTH_SHORT).show();
        }

        else if(binding.etDocNumber.getText().toString().equals("")){
            binding.etDocNumber.setError(getString(R.string.required));
            binding.etDocNumber.setFocusable(true);
        }
        else if(binding.etEmail.getText().toString().equals("")){
            binding.etEmail.setError(getString(R.string.required));
            binding.etEmail.setFocusable(true);
        }

        else if(!binding.etEmail.getText().toString().matches(Constant.emailPattern)){
            Toast.makeText(ErrandSecondAct.this, getString(R.string.wrong_email), Toast.LENGTH_SHORT).show();
        }

        else if(binding.etMobile.getText().toString().equals("")){
            binding.etMobile.setError(getString(R.string.required));
            binding.etMobile.setFocusable(true);
        }


        else if(binding.etAddress.getText().toString().equals("")){
            binding.etAddress.setError(getString(R.string.required));
            binding.etAddress.setFocusable(true);
        }


        else if(binding.etCity.getText().toString().equals("")){
            binding.etCity.setError(getString(R.string.required));
            binding.etCity.setFocusable(true);
        }

        else if(binding.etArrivalSpe.getText().toString().equals("")){
            binding.etArrivalSpe.setError(getString(R.string.required));
            binding.etArrivalSpe.setFocusable(true);
        }

        else {
            if(NetworkAvailablity.checkNetworkStatus(ErrandSecondAct.this)) receiveParcel();
           else Toast.makeText(ErrandSecondAct.this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();
        }


    }

    private void receiveParcel() {
        DataManager.getInstance().showProgressMessage(ErrandSecondAct.this, getString(R.string.please_wait));


        Map<String, String> map = new HashMap<>();
        map.put("user_id",DataManager.getInstance().getUserData(ErrandSecondAct.this).getResult().getId());
        map.put("re_first_name", binding.etName.getText().toString());
        map.put("re_last_name", binding.etSurname.getText().toString());
        map.put("re_documnet_type",docTypeId );
        map.put("re_document_number", binding.etDocNumber.getText().toString());
        map.put("re_email",binding.etEmail.getText().toString() );
        map.put("re_country_code", binding.ccp.getSelectedCountryCode()+"");
        map.put("re_mobile", binding.etMobile.getText().toString());
        map.put("re_address",binding.etAddress.getText().toString() );
        map.put("re_city", binding.etCity.getText().toString());
        map.put("re_lat", SessionManager.readString(ErrandSecondAct.this,Constant.lat,""));
        map.put("re_lon", SessionManager.readString(ErrandSecondAct.this,Constant.lon,""));
        map.put("re_notes", binding.etArrivalSpe.getText().toString());
        Log.e(TAG, "Receive Parcel Request = " + map);
        Call<ResponseBody> call = apiInterface.addReceiverInfoApiCall(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    String responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    Log.e(TAG, "Receive Parcel Response = " + responseString);

                    if (jsonObject.getString("status").equals("1")) {
                        // modelLogin = new Gson().fromJson(responseString, ModelLogin.class);
                        startActivity(new Intent(ErrandSecondAct.this,CreateErrandAct.class));
                    } else {
                        Toast.makeText(ErrandSecondAct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(ErrandSecondAct.this, "Exception = " + e.getMessage(), Toast.LENGTH_SHORT).show();
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


    public void getAllDocType() {
        DataManager.getInstance().showProgressMessage(ErrandSecondAct.this, getString(R.string.please_wait));
        Call<ResponseBody> call = apiInterface.getDocTypes();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    String responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    Log.e(TAG, "Get Doc Type Response = " + responseString);
                    if (jsonObject.getString("status").equals("1")) {
                       DocModel docModel = new Gson().fromJson(responseString, DocModel.class);
                        arrayList.clear();
                        arrayList.addAll(docModel.getResult());
                        typeAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ErrandSecondAct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(ErrandSecondAct.this, "Exception = " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
