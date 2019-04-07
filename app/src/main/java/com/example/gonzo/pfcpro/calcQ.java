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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class calcQ extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private Button calcKapbtn;
    private TextView ergebnissBlind;
    private EditText kap;
    private EditText freq;
    private EditText spannung;
    private calculateKomp calcKap;
    private RadioButton rstern;
    private RadioButton rdreieck;
    private RadioButton rfive;
    private RadioButton rseven;
    private RadioButton rfourteen;
    private RadioGroup sternDreieck;
    private RadioGroup radioVerd;
    private int stDr = 0;
    private int ver = 0;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private Button openHomepage;
    private Button openLinkedIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_q);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_q);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_q);
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

        calcKapbtn =  findViewById(R.id.kapBtn);
        kap = findViewById(R.id.kapTxt);
        spannung =  findViewById(R.id.spanTxt);
        ergebnissBlind = findViewById(R.id.blindView);
        freq = findViewById(R.id.freqTxt);
        rstern = findViewById(R.id.stern);
        rdreieck = findViewById(R.id.dreieck);
        rfive = findViewById(R.id.five);
        rseven = findViewById(R.id.seven);
        rfourteen = findViewById(R.id.fourteen);
        sternDreieck = findViewById(R.id.radioSternDreieck);
        radioVerd = findViewById(R.id.radioVerd);
        calcKap = new calculateKomp();


        sternDreieck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.stern) {
                    stDr = 2;
                } else if(checkedId == R.id.dreieck) {
                    stDr = 1;
                } else {
                    stDr = 0;
                }
            }

        });

        radioVerd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.five) {
                    ver = 1;
                } else if(checkedId == R.id.seven) {
                    ver = 2;
                } else if(checkedId == R.id.fourteen) {
                    ver = 3;
                } else {
                    ver = 0;
                }
            }

        });

        calcKapbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                    if (spannung.getText().toString().length() == 0 || kap.getText().toString().length() == 0 || freq.getText().toString().length() == 0 || stDr == 0) {

                        Toast.makeText(getApplicationContext(), "Geben Sie die Werte ein.", Toast.LENGTH_SHORT).show();

                    } else {

                        double spDouble = Double.parseDouble(spannung.getText().toString());
                        double kapDouble = Double.parseDouble(kap.getText().toString());
                        double freqDouble = Double.parseDouble(freq.getText().toString());
                        ergebnissBlind.setText(Double.toString(calcKap.calcKap(kapDouble, freqDouble, spDouble, stDr, ver )));
                    }


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

            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(home);
        }
        else if(id == R.id.action_leistung){

            Intent verd = new Intent(getApplicationContext(), CalcPower.class);
            startActivity(verd);


        }
        else if(id == R.id.action_verdrosselung){

            Intent verd = new Intent(getApplicationContext(), calc_verd.class);
            startActivity(verd);

        }else if(id == R.id.action_ck){

            Intent ck = new Intent(getApplicationContext(), CalcCK.class);
            startActivity(ck);

        }else if(id == R.id.action_bill){

            Intent bill = new Intent(getApplicationContext(), CalcCK.class);
            startActivity(bill);


        }else if(id == R.id.action_kapazität){


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
