package com.example.gonzo.pfcpro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private Button verdBtn;
    private Button powerBtn;
    private Button kapBtn;
    private Button ckBtn;
    private Button billBtn;
    private Button infoBtn;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private Button openHomepage;
    private Button openLinkedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        verdBtn = findViewById(R.id.buttonVerd);
        powerBtn = findViewById(R.id.buttonPower);
        kapBtn = findViewById(R.id.buttonKap);
        ckBtn = findViewById(R.id.buttonCk);
        billBtn = findViewById(R.id.buttonBill);
        infoBtn = findViewById(R.id.buttonInfo);

        openHomepage = (Button) findViewById(R.id.homepage);
        openLinkedIn = (Button) findViewById(R.id.linkedin);

        openHomepage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.capag-energy.com/"));
                startActivity(browserIntent);
            }
        });

        openLinkedIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/cap-ag/"));
                startActivity(browserIntent);
            }
        });

        verdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent verd = new Intent(getApplicationContext(), calc_verd.class);
                startActivity(verd);

            }
        });



        powerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent power = new Intent(getApplicationContext(), CalcPower.class);
                startActivity(power);

            }
        });

        kapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent kap= new Intent(getApplicationContext(), calcQ.class);
                startActivity(kap);

            }
        });

        ckBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent ck = new Intent(getApplicationContext(), CalcCK.class);
                startActivity(ck);

            }
        });

        billBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent bill = new Intent(getApplicationContext(), CalcBill.class);
                startActivity(bill);

            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent info = new Intent(getApplicationContext(), CalcInfo.class);
                startActivity(info);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onNavigationItemSelected(MenuItem item){

        int id = item.getItemId();

        if (id == R.id.action_home) {
            return true;
        }
        else if(id == R.id.action_leistung){

            Intent power = new Intent(MainActivity.this, CalcPower.class);
            startActivity(power);

        }
        else if(id == R.id.action_verdrosselung){

            Intent verd = new Intent(getApplicationContext(), calc_verd.class);
            startActivity(verd);

        }else if(id == R.id.action_kapazität){

            Intent calcQ = new Intent(getApplicationContext(), calcQ.class);
            startActivity(calcQ);

        }else if(id == R.id.action_ck){

            Intent ck = new Intent(getApplicationContext(), CalcCK.class);
            startActivity(ck);

        }else if(id == R.id.action_bill) {

            Intent bill = new Intent(getApplicationContext(), CalcBill.class);
            startActivity(bill);
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
