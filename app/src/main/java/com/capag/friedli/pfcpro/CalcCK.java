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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class CalcCK extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private TextView ck;
    private EditText ctPrim;
    private EditText ctSek;
    private EditText kleinste;
    private CalculateKomp calcCK;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout mDrawerlayout = findViewById(R.id.drawer_ck);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_ck);
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


        Button calcCKbtn = findViewById(R.id.ckBtn);
        ck =  findViewById(R.id.ck);
        ctPrim = findViewById(R.id.ctPri);
        ctSek =  findViewById(R.id.ctSek);
        kleinste = findViewById(R.id.kleinste);

        calcCK = new CalculateKomp();


        calcCKbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                if(ctPrim.getText().toString().length() == 0 || ctSek.getText().toString().length() == 0 || kleinste.getText().toString().length()== 0 ){

                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.value_toast), Toast.LENGTH_SHORT).show();

                }else {

                    int ctPrimInt = Integer.parseInt(ctPrim.getText().toString());
                    int ctSekInt = Integer.parseInt(ctSek.getText().toString());
                    double kleinsteDouble = Double.parseDouble(kleinste.getText().toString());

                    if(ctPrimInt == 0 || ctSekInt == 0){

                        Toast.makeText(getApplicationContext(), "?", Toast.LENGTH_SHORT).show();

                    } else {
                        ck.setText(Double.toString(calcCK.calcCK(ctPrimInt, ctSekInt, kleinsteDouble)));
                    }
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

            return true;

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
