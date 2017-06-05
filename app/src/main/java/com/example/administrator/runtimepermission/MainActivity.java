package com.example.administrator.runtimepermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    Button previous, next, go;
    EditText url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webView = (WebView) findViewById(R.id.webView);
        previous = (Button) findViewById(R.id.btn_back);
        next = (Button) findViewById(R.id.btn_for);
        go = (Button) findViewById(R.id.btn_go);
        url = (EditText) findViewById(R.id.edit_url);

        go.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);

        //------------------기본적인 webView 세팅 -----------------------
        // script 사용 설정(필수) - 페이지 내의 javaScript 가 동작하도록 한다.
        // 1, 2는 클라이언트를 지정해 주는 것이다.
        // 1. 웹뷰 클라이언트를 지정
        webView.setWebViewClient(new WebViewClient());
        // 2. 둘다 세팅할 것 - 프로토콜에 따라 클라이언트가 선택되는 것으로 파악됨
        webView.setWebChromeClient(new WebChromeClient());
        // 클라이언트 지정 안하면 내장 웹브라우저가 팝업된다
    }

    public void loadUrl(String url){
        if(!url.startsWith("http")){
            url = "http://" + url;
        }
        webView.loadUrl(url);
    }


    // url 도 스택으로 쌓인다.
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                if(webView.canGoBack()){
                    webView.goBack();
                }
                break;
            case R.id.btn_for:
                if(webView.canGoForward()){
                    webView.goForward();
                }

            case R.id.btn_go:
                Toast.makeText(this, "뭐지", Toast.LENGTH_SHORT).show();
                String val = url.getText().toString();
                if(!"".equals(val)){  // 공백이 아닐 경우 처리
                    // 문자열이 url 패턴일 때만
                    if(val.matches("^(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")){
                        loadUrl(val);
                    }
                }

                break;
        }
    }
}
