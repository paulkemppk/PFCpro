package com.example.gonzo.pfcpro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CalcInfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private Button openHomepage;
    private Button openLinkedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_info);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_info);
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
            Intent home = new Intent(CalcInfo.this, MainActivity.class);
            startActivity(home);

        }
        else if(id == R.id.action_leistung){

            Intent power = new Intent(CalcInfo.this, CalcPower.class);
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
