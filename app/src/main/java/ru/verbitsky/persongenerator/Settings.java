package ru.verbitsky.persongenerator;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Set;

public class Settings extends AppCompatActivity{


    Button btContact, btPrivacy;
    LinearLayout lnLanguage, lnAge;

    Configuration config;

    String spinnerAgeRus[] = {"Любой", "20-29 лет","30-39 лет","40-49 лет"};
    String spinnerAgeEng[] = {"Any age", "20-29 y. o.","30-39 y. o.","40-49 y. o."};

    TextView zagolovokLanguage, zagolovokAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        config = new Configuration(getResources().getConfiguration());

        setContentView(R.layout.settings);




        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btContact = findViewById(R.id.buttonContact);
        zagolovokLanguage = findViewById(R.id.zagolovokLanguage);
        zagolovokAge = findViewById(R.id.zagolovokAge);
        lnLanguage = findViewById(R.id.languagelayout);
        btPrivacy = findViewById(R.id.buttonPolicy);

        btPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                operPrivacy();
            }
        });



        btContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNetworkAvailable(Settings.this)) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://t.me/verbser"));
                    startActivity(browserIntent);
                }else{
                    makeToast(getString(R.string.noConnection));
                }

            }
        });


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        final Spinner spinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        String selectedAge = spinnerAge.getSelectedItem().toString();
        ArrayAdapter<?> adapterAge =
                ArrayAdapter.createFromResource(this, R.array.ages, R.layout.spinner_item);
        adapterAge.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapterAge);

        spinnerAge.setSelection(StartDisplay.ageFlag);


        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {



                StartDisplay.ageFlag = selectedItemPosition;



            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String selected = spinner.getSelectedItem().toString();
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.languages, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(StartDisplay.languageFlag);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                if(selectedItemPosition==0){

                    StartDisplay.languageFlag = 0;
                    config.locale = Locale.ENGLISH;
                    toolbar.setTitle("Settings");

                    ArrayAdapter<String> adapterAge =
                            new ArrayAdapter<String>(Settings.this, R.layout.spinner_item, spinnerAgeEng);
                    adapterAge.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    spinnerAge.setAdapter(adapterAge);
                    spinnerAge.setSelection(StartDisplay.ageFlag);
                    zagolovokAge.setText("Age");

                    zagolovokLanguage.setText("Language");
                    btContact.setText("Contact developer");
                    StartDisplay.btSaved.setText("SAVED USERS");

                }else{
                    StartDisplay.languageFlag = 1;
                    Locale myLocale = new Locale("ru","RU");
                    config.locale = myLocale;


                    ArrayAdapter<String> adapterAge =
                            new ArrayAdapter<String>(Settings.this, R.layout.spinner_item, spinnerAgeRus);
                    adapterAge.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    spinnerAge.setAdapter(adapterAge);
                    spinnerAge.setSelection(StartDisplay.ageFlag);
                    zagolovokAge.setText("Возраст");


                    toolbar.setTitle("Настройки");
                    zagolovokLanguage.setText("Язык");
                    btContact.setText("Написать разработчику");
                    StartDisplay.btSaved.setText("СОХРАНЁННЫЕ");

                }

                getResources().updateConfiguration(config, getResources().getDisplayMetrics());


            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    public void makeToast(String text){
        Toast toast = Toast.makeText(Settings.this, text, Toast.LENGTH_SHORT);
        toast.getView().setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_toast));
        toast.show();
    }


    public void operPrivacy() {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/15hD0owuwrqnALJMEs4hj3gMbW9Q1Rn5VzK6iOTbBfKk/edit?usp=sharing"));
        startActivity(browserIntent);


    }


}
