package com.capag.friedli.pfcpro;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Environment;

import java.util.Locale;
import java.util.Objects;


public class CalcBill extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private WebView webb;
    private SwipeRefreshLayout swipe;
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    private ActionBarDrawerToggle mToggle;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bill);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout mDrawerlayout = findViewById(R.id.drawer_bill);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_bill);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();


        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button openHomepage = findViewById(R.id.homepage);
        Button openLinkedIn = findViewById(R.id.linkedin);

        openHomepage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(Locale.getDefault().getLanguage().equals("de")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.capag-energy.com/"));
                    startActivity(browserIntent);
                }else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.capag-energy.com/en/"));
                    startActivity(browserIntent);
                }
            }
        });

        openLinkedIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/cap-ag/"));
                startActivity(browserIntent);
            }
        });

        webb = findViewById(R.id.web1);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webb, true);
        CookieManager.getInstance().setAcceptCookie(true);

        if(Locale.getDefault().getLanguage().equals("de")) {
            webb.loadUrl("https://www.capag-energy.com/blindleistungskompensation/berechnung");
        }else {
            webb.loadUrl("https://www.capag-energy.com/en/power-factor-correction/calculation-tool");
        }

        WebSettings webSettings = webb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.getAllowUniversalAccessFromFileURLs();





        webb.setWebViewClient(new WebViewClient() {


            public void onLoadResource(WebView view, String url) {
                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('navbar navbar-expand-lg')[0].style.display='none'; " +
                        "})()");

                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('breadcrumb')[0].style.display='none'; " +
                        "})()");

                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('footer d-print-none')[0].style.display='none'; " +
                        "})()");
                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('headmedien d-print-none')[0].style.display='none'; " +
                        "})()");
                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('flexslider-wrap')[0].style.display='none'; " +
                        "})()");



            }

            public void onPageFinished(WebView view, String url) {

                swipe.setRefreshing(false);

            }

        });



        // Ermöglicht das öffnen von PDF Dokumenten
        webb.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {

                try {
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse(url));
                    /* Let's have some Cookies !!!*/
                    String cookies = CookieManager.getInstance().getCookie(url);
                    request.addRequestHeader("cookie", cookies);
                    //Yummy !!
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                    final String filename = URLUtil.guessFileName(url, contentDisposition, mimetype);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    assert dm != null;
                    dm.enqueue(request);
                }
                catch(SecurityException e)
                {
                    Toast.makeText(getApplicationContext(),"Please grant the storage permission !",Toast.LENGTH_LONG).show();
                }


            }



        });

        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                webb.loadUrl(webb.getUrl());

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        swipe.getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener =
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if (webb.getScrollY() == 0)
                            swipe.setEnabled(true);
                        else
                            swipe.setEnabled(false);

                    }
                });
    }

    @Override
    public void onStop() {
        swipe.getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
        super.onStop();
    }


    // Bei betätigen der Zurücktaste wird eine Seite zurückgesprungen und nicht das Programm beendet
    public void onBackPressed() {
        if (webb.canGoBack()) {
            webb.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item){

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {

            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(home);
        }
        else if(id == R.id.action_leistung){

            Intent leistung = new Intent(getApplicationContext(), CalcPower.class);
            startActivity(leistung);

        }
        else if(id == R.id.action_verdrosselung){

            Intent verd = new Intent(getApplicationContext(), CalcVerd.class);
            startActivity(verd);

        }else if(id == R.id.action_kapazität){

            Intent verd = new Intent(getApplicationContext(), calcQ.class);
            startActivity(verd);

        }else if(id == R.id.action_ck){

            Intent ck = new Intent(getApplicationContext(), CalcCK.class);
            startActivity(ck);

        } else if(id == R.id.action_bill) {

            return true;

        }else if(id == R.id.action_info) {

            Intent info = new Intent(getApplicationContext(), CalcInfo.class);
            startActivity(info);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
