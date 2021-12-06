package com.curso.ejercicio04;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Web1Fragment extends Fragment {

    String url = "https://elpais.com";
    String userUrl1;

    WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web1, container, false);

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage(getString(R.string.cargando));
        progressDialog.show();

        webView = view.findViewById(R.id.web1);

        if (userUrl1 == null) {
            webView.loadUrl(url);
        } else {
            webView.loadUrl(userUrl1);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
        });

        return view;
    }

    public void receiveUrl1(String data) {
        this.userUrl1 = data;
    }

}
