package com.capag.friedli.pfcpro;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
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
import org.w3c.dom.Document;

import java.util.Locale;


public class CalcBill extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private WebView webb;
    private Document document;
    private SwipeRefreshLayout swipe;
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private Button openHomepage;
    private Button openLinkedIn;




    // Kontrolle ob Verbindung zum Internet vorhanden ist
    public static boolean isNetworkStatusAvialable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null)
                if (netInfos.isConnected())
                    return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_bill);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_bill);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        openHomepage = (Button) findViewById(R.id.homepage);
        openLinkedIn = (Button) findViewById(R.id.linkedin);

        openHomepage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(Locale.getDefault().getLanguage() == "de") {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.capag-energy.com/"));
                    startActivity(browserIntent);
                }else if(Locale.getDefault().getLanguage() == "en"){
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


        webb = (WebView) findViewById(R.id.web1);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webb, true);
        CookieManager.getInstance().setAcceptCookie(true);
        // Übergabe der Startadresse


        if(Locale.getDefault().getLanguage() == "de") {
            webb.loadUrl("https://www.capag-energy.com/blindleistungskompensation/berechnung/");
        }else if(Locale.getDefault().getLanguage() == "en"){
            webb.loadUrl("https://www.capag-energy.com/power-factor-correction/calculation-tool/");
        }



        WebSettings webSettings = webb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.getAllowUniversalAccessFromFileURLs();





        webb.setWebViewClient(new WebViewClient() {


            public void onLoadResource(WebView view, String url) {
                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('header-fixed')[0].style.display='none'; " +
                        "})()");

                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('slider-container  page')[0].style.display='none'; " +
                        "})()");

                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('footer-border')[0].style.display='none'; " +
                        "})()");
                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('col w-6 services')[0].style.display='none'; " +
                        "})()");
                webb.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('col w-2 service-links')[0].style.display='none'; " +
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
                    dm.enqueue(request);
                }
                catch(SecurityException e)
                {
                    Toast.makeText(getApplicationContext(),"Please grant the storage permission !",Toast.LENGTH_LONG).show();
                }


            }



        });

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
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


    private class MyAppViewClient extends WebViewClient {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    public boolean onNavigationItemSelected(MenuItem item){

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

            Intent verd = new Intent(getApplicationContext(), calc_verd.class);
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
