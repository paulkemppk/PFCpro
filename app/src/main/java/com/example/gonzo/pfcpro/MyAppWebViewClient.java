package com.example.gonzo.pfcpro;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Gonzo on 07.07.2017.
 */

// Klasse sorgt dafür, dass jede neue Seite im WebView geöffnet wird und nicht in einem externen browser

public class MyAppWebViewClient extends WebViewClient{

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().endsWith("https://www.capag.ch/blindleistungskompensation/berechnung/")) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }
}