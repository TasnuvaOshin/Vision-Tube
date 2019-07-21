package com.khudrosoft.visiontube.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.khudrosoft.visiontube.R;

public class LoginActivity extends AppCompatActivity {
    public WebView webView;
    public ImageView imageView;
    private VideoView videoView;
    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        webView = findViewById(R.id.webview);
        imageView  = findViewById(R.id.img);
        videoView = findViewById(R.id.videoview);
        videoView.setZOrderOnTop(true);

        WebClient webClient = new WebClient();
        String path = "android.resource://" + getPackageName() + "/" + R.raw.vv;
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();

        imageView.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webView.loadUrl("https://www.w3schools.com/html/html5_video.asp");




        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
               videoView.setVisibility(View.GONE);
            }
        });



        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    imageView.setVisibility(View.GONE);
                    videoView.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);


                }
            }


        });
        webView.setWebViewClient(webClient);
//        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.setBackgroundColor(Color.TRANSPARENT);

        if(isNetworkConnected()) {
            webView.loadUrl("http://v-tube.xyz/login");
          //  videoView.setVisibility(View.GONE);
        }else{
            imageView.setImageResource(R.drawable.nointernet);
            imageView.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    public class WebClient extends WebViewClient {

        @Override
        @TargetApi(Build.VERSION_CODES.M)
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            webView.loadUrl("file:///android_asset/error.html");      }

        @SuppressWarnings("deprecation")
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            webView.loadUrl("file:///android_asset/error.html");
        }

        private void handleError(WebView view, int errorCode, String description, final Uri uri) {
            webView.loadUrl("file:///android_asset/error.html");
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;



        }
    }


}
