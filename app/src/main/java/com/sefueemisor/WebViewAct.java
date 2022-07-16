package com.sefueemisor;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityWebviewBinding;

public class WebViewAct extends AppCompatActivity {
    ActivityWebviewBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_webview);
      initViews();
    }

    private void initViews() {
        binding.ivBack.setOnClickListener(v -> finish());

        if(getIntent()!=null){
            binding.tvTtile.setText(getIntent().getStringExtra("title"));
            startWebView(getIntent().getStringExtra("url"));
        }

    }


    private void startWebView(String url) {
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            @Override
            public void onLoadResource(WebView view, String url) {
                //  DataManager.getInstance().showProgressMessage(PaymentAct.this, getString(R.string.please_wait));
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //  DataManager.getInstance().hideProgressMessage();
                Log.e("url====",url);

                try {


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Load url in webView
        binding.webView.loadUrl(url);
    }


}
