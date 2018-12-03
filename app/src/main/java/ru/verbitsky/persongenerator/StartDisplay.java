package ru.verbitsky.persongenerator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.solovyev.android.checkout.ActivityCheckout;
import org.solovyev.android.checkout.BillingRequests;
import org.solovyev.android.checkout.Checkout;
import org.solovyev.android.checkout.EmptyRequestListener;
import org.solovyev.android.checkout.Inventory;
import org.solovyev.android.checkout.ProductTypes;
import org.solovyev.android.checkout.Purchase;

import java.util.Locale;

import javax.annotation.Nonnull;

public class StartDisplay extends AppCompatActivity implements View.OnClickListener{
    static Button  btSaved;
    ImageButton btRussia, btUSA, btUK, btGermany, btFrance, btMale, btFemale, btSettings;
    LinearLayout lnRussia, lnUsa, lnUk, lnGermany, lnFrance;

    String Country;
    int country;

    static int languageFlag, ageFlag, amountOfEntries;

    Configuration config;



    TextView textViewName1;



    SharedPreferences sPref;


    final String SAVED_TEXT = "lang_flag", SAVED_AGE = "age_flag", ENTRIES = "entries";




    @Override
    protected void onStop() {
        super.onStop();

        sPref = getPreferences(MODE_PRIVATE);

        if(sPref.contains(SAVED_TEXT)) {

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SAVED_TEXT, String.valueOf(languageFlag));
            ed.commit();
        }

        if(sPref.contains(ENTRIES)) {

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(ENTRIES, String.valueOf(amountOfEntries));
            ed.commit();
        }



        if(sPref.contains(SAVED_AGE)) {

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SAVED_AGE, String.valueOf(ageFlag));
            ed.commit();
        }



    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        config = new Configuration(getResources().getConfiguration());

        sPref = getPreferences(MODE_PRIVATE);

        if(sPref.contains(SAVED_TEXT)) {

            languageFlag = Integer.parseInt(sPref.getString(SAVED_TEXT, ""));

            if(languageFlag==0) {
                config.locale = Locale.ENGLISH;
            }else{
                Locale myLocale = new Locale("ru","RU");
                config.locale = myLocale;

            }

            getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        }else{

            if(Locale.getDefault().getLanguage().equals("ru")) {
                languageFlag = 1;

            }else{
                languageFlag = 0;
            }

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SAVED_TEXT, String.valueOf(languageFlag));
            ed.commit();
        }


        if(sPref.contains(ENTRIES)) {

            amountOfEntries = Integer.parseInt(sPref.getString(ENTRIES, ""));


        }else{

            amountOfEntries = 0;

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(ENTRIES, String.valueOf(amountOfEntries));
            ed.commit();
        }



        if(sPref.contains(SAVED_AGE)) {

            ageFlag = Integer.parseInt(sPref.getString(SAVED_AGE, ""));



        }else{
            ageFlag = 0;

            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SAVED_AGE, String.valueOf(ageFlag));
            ed.commit();
        }




        setContentView(R.layout.activity_start);


        btSettings = (ImageButton) findViewById(R.id.buttonSettings);
        btSaved = (Button) findViewById(R.id.buttonSaved);



        lnRussia = findViewById(R.id.linearFlagRussia);
        lnUsa = findViewById(R.id.linearFlagUsa);
        lnUk = findViewById(R.id.linearFlagUk);
        lnGermany = findViewById(R.id.linearFlagGermany);
        lnFrance = findViewById(R.id.linearFlagFrance);

        btMale = (ImageButton) findViewById(R.id.button);
        btFemale = (ImageButton) findViewById(R.id.button2);
        btRussia = (ImageButton) findViewById(R.id.imageButtonRussia);
        btUSA = (ImageButton) findViewById(R.id.imageButtonUsa);
        btUK = (ImageButton) findViewById(R.id.imageButtonUK);
        btGermany = (ImageButton) findViewById(R.id.imageButtonGermany);
        btFrance = (ImageButton) findViewById(R.id.imageButtonFrance);
        textViewName1 = (TextView) findViewById(R.id.textName1);



        country = 0;
        lnRussia.setBackground(getResources().getDrawable(R.drawable.circleshape_white));
        lnUsa.setBackgroundColor(getResources().getColor(R.color.colorInvis));
        lnUk.setBackgroundColor(getResources().getColor(R.color.colorInvis));
        lnGermany.setBackgroundColor(getResources().getColor(R.color.colorInvis));
        lnFrance.setBackgroundColor(getResources().getColor(R.color.colorInvis));


        btSettings.setOnClickListener(this);
        btSaved.setOnClickListener(this);
        btFemale.setOnClickListener(this);
        btMale.setOnClickListener(this);
        btRussia.setOnClickListener(this);
        btUSA.setOnClickListener(this);
        btUK.setOnClickListener(this);
        btGermany.setOnClickListener(this);
        btFrance.setOnClickListener(this);




        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/YanoneKaffeesatz-Bold.ttf");
        textViewName1.setTypeface(type);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonSettings:

                Log.d("Button", "кнопка Настройки");

                Intent intentSettings = new Intent(StartDisplay.this, Settings.class);
                startActivity(intentSettings);

                break;

            case R.id.buttonSaved:

                    Intent intentSave = new Intent(StartDisplay.this, SavedPersons.class);
                    startActivity(intentSave);

                break;

            case R.id.button:
                amountOfEntries++;

                Intent intent = new Intent(StartDisplay.this, MainActivity.class);
                intent.putExtra("sex", "male");
                intent.putExtra("countryNomer", String.valueOf(country));
                intent.putExtra("position", "-1");
                startActivity(intent);

                break;
            case R.id.button2:
                amountOfEntries++;




                Intent intent2 = new Intent(StartDisplay.this, MainActivity.class);
                intent2.putExtra("sex", "female");
                intent2.putExtra("countryNomer", String.valueOf(country));
                intent2.putExtra("position", "-1");
                startActivity(intent2);
                break;

            case R.id.imageButtonRussia:
                country = 0;
                lnRussia.setBackground(getResources().getDrawable(R.drawable.circleshape_white));
                lnUsa.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnUk.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnGermany.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnFrance.setBackgroundColor(getResources().getColor(R.color.colorInvis));

                break;
            case R.id.imageButtonUsa:
                country = 1;

                lnUsa.setBackground(getResources().getDrawable(R.drawable.circleshape_white));
                lnRussia.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnUk.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnGermany.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnFrance.setBackgroundColor(getResources().getColor(R.color.colorInvis));

                break;
            case R.id.imageButtonUK:
                country = 2;
                lnUk.setBackground(getResources().getDrawable(R.drawable.circleshape_white));
                lnUsa.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnRussia.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnGermany.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnFrance.setBackgroundColor(getResources().getColor(R.color.colorInvis));

                break;


            case R.id.imageButtonGermany:
                country = 3;
                lnGermany.setBackground(getResources().getDrawable(R.drawable.circleshape_white));
                lnUsa.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnUk.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnRussia.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnFrance.setBackgroundColor(getResources().getColor(R.color.colorInvis));

                break;

            case R.id.imageButtonFrance:
                country = 4;
                lnFrance.setBackground(getResources().getDrawable(R.drawable.circleshape_white));
                lnUsa.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnUk.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnGermany.setBackgroundColor(getResources().getColor(R.color.colorInvis));
                lnRussia.setBackgroundColor(getResources().getColor(R.color.colorInvis));

                break;


        }
    }


}
