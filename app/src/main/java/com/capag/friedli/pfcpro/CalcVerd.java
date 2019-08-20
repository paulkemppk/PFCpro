package com.capag.friedli.pfcpro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class CalcVerd extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView ergebnissVerdrosselung;
    private TextView ergebnissVerdrosselung2;
    private EditText kap;
    private EditText freq;
    private EditText ind;
    private CalculateKomp calcVerd;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_verd);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button calcVerdbtn = findViewById(R.id.verdBtn);
        kap = findViewById(R.id.spanTxt);
        ind = findViewById(R.id.kapTxt);
        ergebnissVerdrosselung = findViewById(R.id.verdView2);
        ergebnissVerdrosselung2 = findViewById(R.id.verdView);
        freq = findViewById(R.id.freqTxt);
        calcVerd = new CalculateKomp();

        DrawerLayout mDrawerlayout = findViewById(R.id.drawer_verd);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_verd);
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


        calcVerdbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {


                if(ind.getText().toString().length() == 0 || kap.getText().toString().length() == 0 || freq.getText().toString().length()== 0 ){

                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.value_toast), Toast.LENGTH_SHORT).show();

                }else {

                    double indDouble = Double.parseDouble(ind.getText().toString());
                    double kapDouble = Double.parseDouble(kap.getText().toString());
                    double freqDouble = Double.parseDouble(freq.getText().toString());
                    ergebnissVerdrosselung.setText(Double.toString(calcVerd.calcVerd(indDouble,kapDouble,freqDouble)));
                    ergebnissVerdrosselung2.setText(Double.toString(calcVerd.calcVerdinHz(indDouble,kapDouble)));
                }

            }
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

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
        else if(id == R.id.action_verdrosselung){

            return true;

        }else if(id == R.id.action_leistung){

            Intent leistung = new Intent(getApplicationContext(), CalcPower.class);
            startActivity(leistung);

        }else if(id == R.id.action_kapazität){

            Intent verd = new Intent(getApplicationContext(), calcQ.class);
            startActivity(verd);

        }else if(id == R.id.action_ck){

            Intent ck = new Intent(getApplicationContext(), CalcCK.class);
            startActivity(ck);

        }else if(id == R.id.action_bill) {

            Intent bill = new Intent(getApplicationContext(), CalcCK.class);
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
