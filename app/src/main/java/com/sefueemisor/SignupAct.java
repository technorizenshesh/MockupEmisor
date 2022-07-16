package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.sefueemisor.databinding.ActivitySignupBinding;
import com.sefueemisor.retrofit.ApiClient;
import com.sefueemisor.retrofit.Constant;
import com.sefueemisor.retrofit.EmisorInterface;
import com.sefueemisor.utils.DataManager;
import com.sefueemisor.utils.NetworkAvailablity;
import com.sefueemisor.utils.SessionManager;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupAct extends AppCompatActivity {
    public String TAG ="SignupAct";
    ActivitySignupBinding binding;
    String refreshedToken = "";
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    static final int GOOGLE_SIGN_IN_REQUEST_CODE = 1234;
    EmisorInterface apiInterface;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = ApiClient.getClient().create(EmisorInterface.class);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        initViews();
    }

    private void initViews() {

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
            try {
                refreshedToken = instanceIdResult.getToken();
                Log.e("Token===", refreshedToken);
                // Yay.. we have our new token now.
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        binding.btnNext.setOnClickListener(v -> {
            validation();
        });


        FirebaseApp.initializeApp(SignupAct.this);
        callbackManager = CallbackManager.Factory.create();



        //FacebookSdk.setApplicationId(getString(R.string.facebook_key));

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        binding.btnFacebook.setOnClickListener(v -> {
            if(NetworkAvailablity.checkNetworkStatus(SignupAct.this)) fbLogin();
            else Toast.makeText(this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();
        });


        binding.btnGplus.setOnClickListener(v -> {
            if(NetworkAvailablity.checkNetworkStatus(SignupAct .this)) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE);
              /*  Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(50);
                anim.setStartOffset(20);
                anim.setRepeatMode(Animation.REVERSE);*/

            }
            else Toast.makeText(this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();

        });



    }

    private void validation() {
        if(binding.etEmail.getText().toString().equals("")){
            Toast.makeText(SignupAct.this, getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
        }
        else if(!binding.etEmail.getText().toString().matches(Constant.emailPattern)){
            Toast.makeText(SignupAct.this, getString(R.string.wrong_email), Toast.LENGTH_SHORT).show();
        }
        else if(binding.etName.getText().toString().equals("")){
            Toast.makeText(SignupAct.this, getString(R.string.please_enter_user_name), Toast.LENGTH_SHORT).show();
        }
        else if(binding.etPassword.getText().toString().equals("")){
            Toast.makeText(SignupAct.this, getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
        }
        else if(binding.etConPassword.getText().toString().equals("")){
            Toast.makeText(SignupAct.this, getString(R.string.please_enter_confirm_password), Toast.LENGTH_SHORT).show();
        }

        else if(!binding.etConPassword.getText().toString().equals(binding.etPassword.getText().toString())){
            Toast.makeText(SignupAct.this, getString(R.string.password_dont_matched), Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(this,MobileRisAct.class)
            .putExtra("email",binding.etEmail.getText().toString())
            .putExtra("userName",binding.etName.getText().toString())
            .putExtra("password",binding.etPassword.getText().toString()));
        }

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

    public void ShowHidePass11(View view){

        if(view.getId()==R.id.show_pass_btn11){

            if(binding.etConPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.eye);

                //Show Password
                binding.etConPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.blind);

                //Hide Password
                binding.etConPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }


    private void fbLogin() {
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(SignupAct.this);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.e("kjsgdfkjdgsf","onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("kjsgdfkjdgsf","error = " + error.getMessage());
            }

        });

      /*  Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        binding.btnFacebook.startAnimation(anim);*/

    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();

                            if(user !=  null) {
                                Log.e("Google Plus",String.valueOf(user.getPhotoUrl()));
                                if(NetworkAvailablity.checkNetworkStatus(SignupAct.this)) socialLogin(user.getDisplayName(), user.getEmail(), String.valueOf(user.getPhotoUrl()), user.getUid());
                                else Toast.makeText(SignupAct.this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show(); }

                            // updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            // Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });

    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            String profilePhoto = "https://graph.facebook.com/" + token.getUserId() + "/picture?height=500";

                            Log.e("kjsgdfkjdgsf","profilePhoto = " + profilePhoto);
                            if(NetworkAvailablity.checkNetworkStatus(SignupAct.this)) socialLogin(user.getDisplayName(), user.getEmail(), profilePhoto, user.getUid());
                            else Toast.makeText(SignupAct.this, getString(R.string.network_failure), Toast.LENGTH_SHORT).show();


                            // updateUI(user);

                        } else {
                            Log.e("facebook=======",task.getException().getLocalizedMessage());
                            Toast.makeText(SignupAct.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // updateUI(null);

                        }

                        // ...

                    }
                });
    }



    private void socialLogin(String name, String email, String profilePhoto, String uid) {
        DataManager.getInstance().showProgressMessage(SignupAct.this, getString(R.string.please_wait));
        Map<String, String> map = new HashMap<>();
        map.put("user_name", name);
        map.put("email", email);
        map.put("address", "");
        map.put("mobile","" );
        map.put("country_code","" );
        map.put("lat","" );
        map.put("lon","" );
        map.put("password","" );
        map.put("social_id", uid);
        map.put("register_id", refreshedToken);

        Log.e(TAG, "SOCIAL LOGIN REQUEST" + map);
        Call<ResponseBody> loginCall = apiInterface.socialLogin( map);
        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DataManager.getInstance().hideProgressMessage();
                try {
                    String responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    Log.e(TAG, "Login Response = " + responseString);

                    if (jsonObject.getString("status").equals("1")) {
                        // modelLogin = new Gson().fromJson(responseString, ModelLogin.class);
                        SessionManager.writeString(SignupAct.this, Constant.USER_INFO, responseString);
                        startActivity(new Intent(SignupAct.this, HomeAct.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();

                    } else {
                        Toast.makeText(SignupAct.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                call.cancel();
                DataManager.getInstance().hideProgressMessage();
            }

        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            /* Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);*/
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                e.getStatusCode();
                // ...
            }

        }

    }


}
