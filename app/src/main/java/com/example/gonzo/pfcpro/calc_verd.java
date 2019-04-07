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
import android.widget.TextView;
import android.widget.Toast;

public class calc_verd extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button calcVerdbtn;
    private TextView ergebnissVerdrosselung;
    private EditText kap;
    private EditText freq;
    private EditText ind;
    private calculateKomp calcVerd;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private Button openHomepage;
    private Button openLinkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_verd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calcVerdbtn = (Button) findViewById(R.id.verdBtn);
        kap = (EditText) findViewById(R.id.spanTxt);
        ind = (EditText) findViewById(R.id.kapTxt);
        ergebnissVerdrosselung = (TextView) findViewById(R.id.verdView);
        freq = (EditText) findViewById(R.id.freqTxt);
        calcVerd = new calculateKomp();

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_verd);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_verd);
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


        calcVerdbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(ind.getText().toString().length() == 0 || kap.getText().toString().length() == 0 || freq.getText().toString().length()== 0 ){

                    Toast.makeText(getApplicationContext(), "Geben Sie die Werte ein.", Toast.LENGTH_SHORT).show();

                }else {

                    double indDouble = Double.parseDouble(ind.getText().toString());
                    double kapDouble = Double.parseDouble(kap.getText().toString());
                    double freqDouble = Double.parseDouble(freq.getText().toString());
                    ergebnissVerdrosselung.setText(Double.toString(calcVerd.calcVerd(indDouble,kapDouble,freqDouble)));
                }

            }
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

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
