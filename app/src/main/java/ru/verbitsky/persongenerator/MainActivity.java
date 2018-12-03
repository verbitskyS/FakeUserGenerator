package ru.verbitsky.persongenerator;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import org.solovyev.android.checkout.ActivityCheckout;
import org.solovyev.android.checkout.BillingRequests;
import org.solovyev.android.checkout.Checkout;
import org.solovyev.android.checkout.EmptyRequestListener;
import org.solovyev.android.checkout.Inventory;
import org.solovyev.android.checkout.ProductTypes;
import org.solovyev.android.checkout.Purchase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.Nonnull;

import exceptions.CityNotFound;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    String russiaString, usaString, ukString, zagolovokNameString, zagolovokCityString;

    ImageButton btBack, btSave, btDownload;


    DBHelper dbHelper;
    int position;

    CustomDialogClass dialogRating;

    Country country;


    String Country="", CountryNomer, sex;

    static TextView txName, txLastName, txFatherName, txCity, txCountry, txSubject, txPostCode, txPhone, txAdres,
            txNameEng, txLastNameEng, txFatherNameEng, txCityEng, txCountryEng, txSubjectEng, txAdresEng, txPassword, txEmail, txLogin;

    static TextView txAge, txCard, txCVC, txDateCard, txZagolovokName, txZagolovokCity, txZagolovokSubject, txPhotoFrom;

    static ImageView flag, sexImage;

    static LinearLayout lnAd, imageLinear, firstNameLinear, lastNameLinear, fatherNameLinear, birthdayLinear, nationalLinear, subjectLinear, cityLinear,
            addressLinear, postcodeLinear, phoneNumberLinear, emailLinear, loginLinear, passwordLinear, visaLinear, cvc2Linear, expDateLinear;

    static int flatInt=0;
    static int homeInt=0;
    static int streetInt=0;
    static int cityInt=0;
    static int nameInt=0;
    static int lastNameInt=0;
    static int fatherNameInt=0;
    int i;
    int flagSave=0;

    RandomUser User;

    UserFromData dataUser;



    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.wtf("MainActivity.class", "Приложение закрыто");

        dbHelper.close();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.wtf("MainActivity.class", "Мало оперативной памяти");
        dbHelper.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        Log.d("MainActivity.class", "Начинаем инициализировать view-элементы из разметки и заполянть их данными");


        dbHelper = new DBHelper(this);




        russiaString = getString(R.string.russia);
        usaString = getString(R.string.usa);
        ukString = getString(R.string.uk);
        zagolovokNameString = getString(R.string.firstName2);
        zagolovokCityString = getString(R.string.city2);

        btBack = findViewById(R.id.buttonBack);
        btSave = findViewById(R.id.buttonSave);
        btDownload = findViewById(R.id.buttonDownload);


        txName = findViewById(R.id.txFirstName);
        txLastName = findViewById(R.id.txLastName);
        txFatherName = findViewById(R.id.txFatherName);
        txCity = findViewById(R.id.txCity);
        txCountry = findViewById(R.id.txCountry);
        txSubject = findViewById(R.id.txSubject);
        txPostCode = findViewById(R.id.txPostCode);
        txPhone = findViewById(R.id.txPhone);
        txAdres = findViewById(R.id.txAdress);
        txPassword = findViewById(R.id.txWeight);
        txEmail = findViewById(R.id.txСolorHair);
        txLogin = findViewById(R.id.textviewUsername);
        txZagolovokName = findViewById(R.id.textViewFirstName);
        txZagolovokCity = findViewById(R.id.textViewCityandPostcode);
        txZagolovokSubject = findViewById(R.id.textViewzagolovokSubject);

        txPhotoFrom = findViewById(R.id.textViewPhotoFrom);


        firstNameLinear = findViewById(R.id.firstnamelinear);
        lastNameLinear = findViewById(R.id.lastnamelinear);
        fatherNameLinear = findViewById(R.id.fathernamelinear);
        birthdayLinear = findViewById(R.id.birthdaylinear);
        nationalLinear = findViewById(R.id.nationlinear);
        subjectLinear = findViewById(R.id.subjectlinear);
        cityLinear = findViewById(R.id.citylinear);
        addressLinear = findViewById(R.id.addresslinear);
        postcodeLinear = findViewById(R.id.postcodelinear);
        phoneNumberLinear = findViewById(R.id.phonenumberlinear);
        emailLinear = findViewById(R.id.emaillinear);
        loginLinear = findViewById(R.id.usernamelinear);
        passwordLinear = findViewById(R.id.passwordlinear);
        visaLinear = findViewById(R.id.visalinear);
        cvc2Linear = findViewById(R.id.cvc2linear);
        expDateLinear = findViewById(R.id.expdatelinear);
        //lnAd = findViewById(R.id.lnAdd);

        firstNameLinear.setOnClickListener(this);
        lastNameLinear.setOnClickListener(this);
        fatherNameLinear.setOnClickListener(this);
        birthdayLinear.setOnClickListener(this);
        nationalLinear.setOnClickListener(this);
        subjectLinear.setOnClickListener(this);
        cityLinear.setOnClickListener(this);
        addressLinear.setOnClickListener(this);
        postcodeLinear.setOnClickListener(this);
        phoneNumberLinear.setOnClickListener(this);
        emailLinear.setOnClickListener(this);
        loginLinear.setOnClickListener(this);
        passwordLinear.setOnClickListener(this);
        visaLinear.setOnClickListener(this);
        cvc2Linear.setOnClickListener(this);
        expDateLinear.setOnClickListener(this);


        txNameEng = findViewById(R.id.txFirstNameEng);
        txLastNameEng = findViewById(R.id.txLastNameEng);
        txFatherNameEng = findViewById(R.id.txFatherNameEng);
        txCityEng = findViewById(R.id.txCityEng);
        txCountryEng = findViewById(R.id.txCountryEng);
        txSubjectEng = findViewById(R.id.txSubjectEng);
        txAdresEng = findViewById(R.id.txAdressEng);

        txNameEng.setVisibility(View.GONE);
        lastNameLinear.setVisibility(View.GONE);
        fatherNameLinear.setVisibility(View.GONE);
        subjectLinear.setVisibility(View.GONE);
        txAdresEng.setVisibility(View.GONE);
        txCountryEng.setVisibility(View.GONE);
        txCityEng.setVisibility(View.GONE);
        postcodeLinear.setVisibility(View.GONE);


        if (StartDisplay.amountOfEntries == 4) {
            dialogRating = new CustomDialogClass(MainActivity.this);
            dialogRating.show();
        }

        imageLinear = findViewById(R.id.imagelinear);


        txCard = findViewById(R.id.txCard);
        txCVC = findViewById(R.id.txCVC);
        txDateCard = findViewById(R.id.txCardDate);


        flag = findViewById(R.id.imageView5);
        sexImage = findViewById(R.id.imageView4);
        //txBirthday = (TextView) findViewById(R.id.txDate);
        txAge = findViewById(R.id.txAge);
        Intent intent = getIntent();
        sex = intent.getStringExtra("sex");
        CountryNomer = intent.getStringExtra("countryNomer");
        position = Integer.parseInt(intent.getStringExtra("position"));


        try {


            switch (CountryNomer) {
                case "0": //РОССИЯ
                    flag.setImageResource(R.drawable.russia_flag);

                    txZagolovokCity.setText(R.string.city);
                    txZagolovokName.setText(R.string.firstName);

                    txNameEng.setVisibility(View.VISIBLE);
                    lastNameLinear.setVisibility(View.VISIBLE);
                    fatherNameLinear.setVisibility(View.VISIBLE);
                    subjectLinear.setVisibility(View.VISIBLE);
                    txAdresEng.setVisibility(View.VISIBLE);
                    txCountryEng.setVisibility(View.VISIBLE);
                    txCityEng.setVisibility(View.VISIBLE);
                    postcodeLinear.setVisibility(View.VISIBLE);

                    txSubject.setVisibility(View.VISIBLE);

                    txCountry.setText(russiaString);
                    txCountryEng.setText("Russia");

                    if (StartDisplay.languageFlag == 0) {

                        txCountry.setText("Russia");
                        txCountryEng.setText(russiaString);

                        txName = findViewById(R.id.txFirstNameEng);
                        txLastName = findViewById(R.id.txLastNameEng);
                        txFatherName = findViewById(R.id.txFatherNameEng);
                        txCity = findViewById(R.id.txCityEng);
                        txCountry = findViewById(R.id.txCountryEng);
                        txSubject = findViewById(R.id.txSubjectEng);
                        txAdres = findViewById(R.id.txAdressEng);

                        txNameEng = findViewById(R.id.txFirstName);
                        txLastNameEng = findViewById(R.id.txLastName);
                        txFatherNameEng = findViewById(R.id.txFatherName);
                        txCityEng = findViewById(R.id.txCity);
                        txCountryEng = findViewById(R.id.txCountry);
                        txSubjectEng = findViewById(R.id.txSubject);
                        txAdresEng = findViewById(R.id.txAdress);

                    }


                    User = new RandomUser(CountryNomer, sex);
                    dataUser = new UserFromData(dbHelper, User);

                    if(position==-1) {

                        User.startRandom();

                    }else{
                        dataUser.downloadUser(position);

                    }

                    break;


                case "1": //США

                    flag.setImageResource(R.drawable.usa_flag);

                    txZagolovokCity.setText(R.string.city2);
                    txZagolovokName.setText(R.string.firstName2);


                    break;


                case "2":

                    flag.setImageResource(R.drawable.uk_flag);


                    subjectLinear.setVisibility(View.VISIBLE);
                    txSubjectEng.setVisibility(View.GONE);

                    txZagolovokSubject.setText(getString(R.string.county));


                    break;


                case "3": //Германия

                    flag.setImageResource(R.drawable.germany_flag);

                    txZagolovokSubject.setText(getString(R.string.state));

                    subjectLinear.setVisibility(View.VISIBLE);
                    txSubjectEng.setVisibility(View.GONE);

                    txZagolovokCity.setText(R.string.city2);
                    txZagolovokName.setText(R.string.firstName2);


                    break;


                case "4": //Франция

                    flag.setImageResource(R.drawable.france_flag);

                    txZagolovokCity.setText(R.string.cityFrance);
                    txZagolovokName.setText(R.string.firstName2);

                    break;

            }


            if (!CountryNomer.equals("0")) {
                User = new RandomUser(CountryNomer, sex);
                dataUser = new UserFromData(dbHelper, User);

                if (position == -1) {


                    User.startRandom();

                } else {


                    dataUser.downloadUser(position);
                }

            }


            if (sex.equals("male")) {

                imageLinear.setBackgroundResource(R.drawable.imageviewshape);

                Picasso.get().load("http://randomuser.ru/images/men/" +  Data.photoString + ".jpg").into(sexImage);

            } else {

                imageLinear.setBackgroundResource(R.drawable.imageviewshapewoman);

                Picasso.get().load("http://randomuser.ru/images/women/" +  Data.photoString + ".jpg").into(sexImage);
            }






            btDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("MainActivity.class", "Нажали на кнопку сохранения фотографии!");

                    if (isNetworkAvailable(MainActivity.this)) {

                        String url;

                        if (sex.equals("male")) {

                            url = "http://randomuser.ru/images/men/" + Data.photoString + ".jpg";

                        } else {

                            url = "http://randomuser.ru/images/women/" + Data.photoString + ".jpg";

                        }

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);


                    } else {
                        makeToast(getString(R.string.noConnection));
                    }
                }
            });


            btBack.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Log.d("MainActivity.class", "Нажали на кнопку назад!");
                    finish();

                }
            });

            if (position != -1) {
                flagSave = 1;
                btSave.setImageDrawable((getResources().getDrawable(R.drawable.ic_delete_forever_black_24dp)));
            }

            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("MainActivity.class", "Нажали на кнопку сохранения пользователя!");


                    if (flagSave == 0) {

                        dataUser.saveUser();

                        btSave.animate().rotation(360);
                        btSave.setImageDrawable((getResources().getDrawable(R.drawable.ic_delete_forever_black_24dp)));
                        flagSave = 1;
                        makeToast("Saved!");

                    } else {

                        dataUser.deleteUser();
                        flagSave = 0;
                        btSave.setImageDrawable((getResources().getDrawable(R.drawable.ic_save_black_24dp)));
                        makeToast("Deleted!");

                    }
                }

            });


        }catch (Exception e){


            Log.e("MainActivity.class", "Ошибка в загрузке данных: "+e.getMessage(), e);

        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){


            case R.id.firstnamelinear:
                if(CountryNomer.equals("0")) {
                    copyText(txName.getText().toString(), getString(R.string.firstName), "o");
                }else{
                    copyText(txName.getText().toString(), getString(R.string.name), "o");
                }
                break;
            case R.id.lastnamelinear:
                copyText(txLastName.getText().toString(), getString(R.string.lastName), "a");
                break;
            case R.id.fathernamelinear:
                copyText(txFatherName.getText().toString(), getString(R.string.secondName), "o");
                break;
            case R.id.birthdaylinear:
                copyText(txAge.getText().toString(), getString(R.string.birthDay), "a");
                break;
            case R.id.nationlinear:
                copyText(txCountry.getText().toString(), getString(R.string.nationality), "o");
                break;
            case R.id.subjectlinear:
                copyText(txSubject.getText().toString(), getString(R.string.subject), "");
                break;
            case R.id.citylinear:

                if((CountryNomer.equals("1"))||(CountryNomer.equals("3"))) {
                    copyText(txCity.getText().toString(), getString(R.string.city2), "i");
                }else{
                    if(CountryNomer.equals("4")){
                        copyText(txCity.getText().toString(), getString(R.string.cityFrance), "i");
                    }else {
                        copyText(txCity.getText().toString(), getString(R.string.city), "");
                    }
                }
                break;
            case R.id.addresslinear:
                copyText(txAdres.getText().toString(), getString(R.string.address), "");
                break;
            case R.id.postcodelinear:
                copyText(txPostCode.getText().toString(), getString(R.string.postcode), "");
                break;
            case R.id.phonenumberlinear:
                copyText(txPhone.getText().toString(), getString(R.string.phoneNumber), "");
                break;
            case R.id.emaillinear:
                copyText(txEmail.getText().toString(), getString(R.string.addressMail), "");
                break;
            case R.id.usernamelinear:
                copyText(txLogin.getText().toString(), getString(R.string.username),"");
                break;
            case R.id.passwordlinear:
                copyText(txPassword.getText().toString(), getString(R.string.password), "");
                break;
            case R.id.visalinear:
                copyText(txCard.getText().toString(), getString(R.string.cardNumber), "");
                break;
            case R.id.cvc2linear:
                copyText(txCVC.getText().toString(), "CVC2", "");
                break;
            case R.id.expdatelinear:
                copyText(txDateCard.getText().toString(), getString(R.string.expirationDate), "a");
                break;

        }

    }


    public void copyText(String text, String whatCopiied, String okonchanie){

        Log.d("MainActivity.class", "Сохранили текст в буфер обмена: "+whatCopiied);

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copied_text", text);
        clipboard.setPrimaryClip(clip);

        switch (okonchanie){
            case "":  makeToast(whatCopiied+" "+getString(R.string.copy));
            break;
            case "a": makeToast(whatCopiied+" "+getString(R.string.copyA));
                break;
            case "o":  makeToast(whatCopiied+" "+getString(R.string.copyO));
                break;
            case "i":  makeToast(whatCopiied+" "+getString(R.string.copyI));
                break;

        }


    }


    public void makeToast(String text){

        Log.d("MainActivity.class", "Отобразили сообщение(тост): "+ text);

        Toast toast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_toast));
        TextView textToast = (TextView) view.findViewById(android.R.id.message);
        /*Here you can do anything with above textview like text.setTextColor(Color.parseColor("#000000"));*/
        toast.show();
    }




    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()){
            Log.d("MainActivity.class", "Есть доступ к интернету");

            return true;

        }else {
            Log.w("MainActivity.class", "Нет доступа к интернету");
            return false;
        }
    }



}