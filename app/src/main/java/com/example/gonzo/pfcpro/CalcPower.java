package com.example.gonzo.pfcpro;

import android.content.Intent;
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


public class CalcPower extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Button calcPowerbtn;
    private TextView blindleistung;
    private EditText leistung;
    private EditText cosphi;
    private EditText zcosphi;
    private calculateKomp calcPowr;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_power);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_power);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_power);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        calcPowerbtn =  findViewById(R.id.powerBtn);
        blindleistung =  findViewById(R.id.blindTxt);
        leistung = findViewById(R.id.ctPri);
        cosphi =  findViewById(R.id.cosphi);
        zcosphi = findViewById(R.id.zcosphi);

        calcPowr = new calculateKomp();


        calcPowerbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(leistung.getText().toString().length() == 0 || cosphi.getText().toString().length() == 0 || zcosphi.getText().toString().length()== 0 ){

                    Toast.makeText(getApplicationContext(), "Geben Sie die Werte ein.", Toast.LENGTH_SHORT).show();

                }else {

                    double leistungDouble = Double.parseDouble(leistung.getText().toString());
                    double cosphiDouble = Double.parseDouble(cosphi.getText().toString());
                    double zcosDouble = Double.parseDouble(zcosphi.getText().toString());
                    blindleistung.setText(Double.toString(calcPowr.calcPowr(leistungDouble,cosphiDouble,zcosDouble)));
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

            return true;

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
