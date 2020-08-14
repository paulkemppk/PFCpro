package com.capag.friedli.pfcpro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class calcQ extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private TextView ergebnissBlind;
    private EditText kap;
    private EditText freq;
    private EditText spannung;
    private CalculateKomp calcKap;
    private int stDr = 0;
    private int ver = 0;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_q);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout mDrawerlayout = findViewById(R.id.drawer_q);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_q);
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

        Button calcKapbtn = findViewById(R.id.kapBtn);
        kap = findViewById(R.id.kapTxt);
        spannung =  findViewById(R.id.spanTxt);
        ergebnissBlind = findViewById(R.id.blindView);
        freq = findViewById(R.id.freqTxt);
        /*RadioButton rstern = findViewById(R.id.stern);
        RadioButton rdreieck = findViewById(R.id.dreieck);
        RadioButton rfive = findViewById(R.id.five);
        RadioButton rseven = findViewById(R.id.seven);
        RadioButton rfourteen = findViewById(R.id.fourteen);*/
        RadioGroup sternDreieck = findViewById(R.id.radioSternDreieck);
        RadioGroup radioVerd = findViewById(R.id.radioVerd);
        calcKap = new CalculateKomp();


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
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                    if (spannung.getText().toString().length() == 0 || kap.getText().toString().length() == 0 || freq.getText().toString().length() == 0 || stDr == 0) {

                        Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.value_toast), Toast.LENGTH_SHORT).show();

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

    public boolean onNavigationItemSelected(@NonNull MenuItem item){

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
            Intent verd = new Intent(getApplicationContext(), CalcVerd.class);
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
