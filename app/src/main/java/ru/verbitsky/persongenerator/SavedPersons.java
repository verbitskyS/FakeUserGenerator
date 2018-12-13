package ru.verbitsky.persongenerator;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SavedPersons extends AppCompatActivity {

    LinearLayout mainLayout, russiaLayout, usaLayout, ukLayout, germanyLayout, franceLayout;
    float scale;
    DBHelper dbHelper;
    ContentValues cv;
    SQLiteDatabase db;
    Cursor cursor;
    TextView textViewNoSaved;

    int idColIndex;
    int idStolbcaColIndex;
    int nameColIndex;
    int nomerColIndex;
    int sexColIndex;
    int countryColIndex;
    int nameEngRusColIndex;

    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_persons);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        dbHelper = new DBHelper(this);
        cv = new ContentValues();
        db = dbHelper.getWritableDatabase();

        scale = getResources().getDisplayMetrics().density;
        mainLayout = findViewById(R.id.mainlayout);
        russiaLayout = findViewById(R.id.russialayout);
        usaLayout = findViewById(R.id.usalayout);
        ukLayout = findViewById(R.id.uklayout);
        germanyLayout = findViewById(R.id.germanylayout);
        franceLayout = findViewById(R.id.francelayout);

        textViewNoSaved = findViewById(R.id.textViewNoSaved);

        russiaLayout.setVisibility(View.GONE);
        usaLayout.setVisibility(View.GONE);
        ukLayout.setVisibility(View.GONE);
        germanyLayout.setVisibility(View.GONE);
        franceLayout.setVisibility(View.GONE);



        cursor = db.query("mytable", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false

        cursor.moveToFirst();
        if (cursor.moveToFirst()) {


            idColIndex = cursor.getColumnIndex("id");
            idStolbcaColIndex = cursor.getColumnIndex("idStolbca");
            nameColIndex = cursor.getColumnIndex("name");
            nameEngRusColIndex = cursor.getColumnIndex("nameEng");
            nomerColIndex = cursor.getColumnIndex("photoName");
            sexColIndex = cursor.getColumnIndex("sex");
            countryColIndex = cursor.getColumnIndex("countryNomer");

            textViewNoSaved.setVisibility(View.GONE);

            do {

                if ((StartDisplay.languageFlag==0)&&(cursor.getString(countryColIndex).equals("0"))){
                    addLinear(cursor.getPosition(), cursor.getString(nomerColIndex),cursor.getString(nameEngRusColIndex), cursor.getString(sexColIndex),  cursor.getString(countryColIndex), cursor.getString(idStolbcaColIndex));

                }else {
                    addLinear(cursor.getPosition(), cursor.getString(nomerColIndex), cursor.getString(nameColIndex), cursor.getString(sexColIndex), cursor.getString(countryColIndex), cursor.getString(idStolbcaColIndex));
                }

            } while (cursor.moveToNext());
        } else{

            textViewNoSaved.setVisibility(View.VISIBLE);
            textViewNoSaved.setText(R.string.notsaved);
        }

        cursor.close();

        dbHelper.close();

        // получаем значения по номерам столбцов и пишем все в лог

    }


    @Override
    protected void onPause() {
        super.onPause();


    }

    private void addLinear(final int position, String nomerPhoto, String name, final String sex, final String country, final String idStolbca){

        final LinearLayout linearLayout = new LinearLayout(SavedPersons.this);
        LinearLayout.LayoutParams linearlayoutLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setLayoutParams(linearlayoutLayoutParams);

        TextView textView = new TextView(SavedPersons.this);
        LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setText(name);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 21);
        textView.setTextColor(getResources().getColor(R.color.colorBlack));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textViewLayoutParams.setMargins((int)(13*scale + 0.5f), 0,0,0);
        textView.setLayoutParams(textViewLayoutParams);



        LinearLayout linearLayoutImage = new LinearLayout(SavedPersons.this);
        linearLayoutImage.setElevation((int)(5*scale + 0.5f));
        LinearLayout.LayoutParams linearlayoutLayoutParamsImage = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayoutImage.setLayoutParams(linearlayoutLayoutParamsImage);
        linearLayoutImage.setGravity(Gravity.CENTER);
        linearlayoutLayoutParamsImage.setMargins((int)(5*scale + 0.5f), (int)(5*scale + 0.5f),(int)(5*scale + 0.5f),(int)(5*scale + 0.5f));
        setDimensions(linearLayoutImage, (int)(63*scale + 0.5f),(int)(63*scale + 0.5f));

        if(sex.equals("male")) {
            linearLayoutImage.setBackgroundResource(R.drawable.imageviewshape);
        }else{
            linearLayoutImage.setBackgroundResource(R.drawable.imageviewshapewoman);
        }


        de.hdodenhof.circleimageview.CircleImageView imageView = new de.hdodenhof.circleimageview.CircleImageView(SavedPersons.this);
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageViewLayoutParams);
        setDimensions(imageView, (int)(60*scale + 0.5f),(int)(60*scale + 0.5f));



        if(sex.equals("male")) {
            if (isNetworkAvailable(SavedPersons.this)) {
                Picasso.get().load("http://randomuser.ru/images/men/med/"+nomerPhoto+".jpg").into(imageView);
            }else{
                Picasso.get().load(R.drawable.male).into(imageView);
            }

        }else{
            if (isNetworkAvailable(SavedPersons.this)) {
                Picasso.get().load("http://randomuser.ru/images/women/med/"+nomerPhoto+".jpg").into(imageView);
            }else{
                Picasso.get().load(R.drawable.female).into(imageView);
            }
        }


        LinearLayout linearLayoutButton = new LinearLayout(SavedPersons.this);
        LinearLayout.LayoutParams linearlayoutLayoutParamsImageButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayoutButton.setLayoutParams(linearlayoutLayoutParamsImageButton);
        linearLayoutButton.setGravity(Gravity.RIGHT);

        final ImageButton deleteButton = new ImageButton(SavedPersons.this);
        LinearLayout.LayoutParams deleteButtonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        deleteButton.setLayoutParams(deleteButtonLayoutParams);

        setDimensions(deleteButton, (int)(26*scale + 0.5f), (int)(26*scale + 0.5f));
        deleteButton.setBackgroundResource(R.drawable.ic_clear_black_24dp);
        deleteButtonLayoutParams.gravity = Gravity.CENTER_VERTICAL|Gravity.RIGHT;
        deleteButtonLayoutParams.setMargins(0, 0,(int)(2*scale + 0.5f),0);

        deleteButton.setLayoutParams(deleteButtonLayoutParams);
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);



        linearLayoutButton.addView(deleteButton);
        linearLayoutImage.addView(imageView);
        linearLayout.addView(linearLayoutImage);
        linearLayout.addView(textView);
        linearLayout.addView(linearLayoutButton);


        switch (country){
            case "0":

                russiaLayout.setVisibility(View.VISIBLE);
                russiaLayout.addView(linearLayout);
                break;

            case "1":

                usaLayout.setVisibility(View.VISIBLE);
                usaLayout.addView(linearLayout);
                break;

            case "2":

                ukLayout.setVisibility(View.VISIBLE);
                ukLayout.addView(linearLayout);
                break;

            case "3":

                germanyLayout.setVisibility(View.VISIBLE);
                germanyLayout.addView(linearLayout);
                break;

            case "4":

                franceLayout.setVisibility(View.VISIBLE);
                franceLayout.addView(linearLayout);
                break;

        }



        linearLayout.setBackground(getResources().getDrawable(R.drawable.ripple_effect));

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SavedPersons.this, MainActivity.class);
                intent.putExtra("sex", sex);
                intent.putExtra("countryNomer", country);
                intent.putExtra("idStolbca", String.valueOf(idStolbca));
                finish();
                startActivity(getIntent());
                startActivity(intent);

            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = dbHelper.getWritableDatabase();
                int delCount =   db.delete("mytable", "idStolbca=" + idStolbca, null);

                deleteButton.animate().rotation(360);
                animateView(linearLayout);

                boolean haveMoreCountry = false;

                Cursor cursor2 = db.query("mytable", null, null, null, null, null, null);

                cursor2.moveToFirst();

                if (cursor2.moveToFirst()) {
                    do {
                        if(cursor2.getString(cursor2.getColumnIndex("countryNomer")).equals(country)){
                            haveMoreCountry = true;
                            break;
                        }
                    } while (cursor.moveToNext());

                    textViewNoSaved.setVisibility(View.GONE);

                }else{
                    textViewNoSaved.setVisibility(View.VISIBLE);
                    textViewNoSaved.setText(R.string.notsaved);

                }
                cursor2.close();

                if(haveMoreCountry==false){

                    switch (country){
                        case "0":
                            animateView(russiaLayout);
                            break;
                        case "1":
                            animateView(usaLayout);
                            break;
                        case "2":
                            animateView(ukLayout);
                            break;
                        case "3":
                            animateView(germanyLayout);
                            break;
                        case "4":
                            animateView(franceLayout);
                            break;
                    }
                }

                dbHelper.close();
            }
        });


    }


    private void setDimensions(View view, int width, int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private void animateView(final View view){
        view.animate().alpha(0).setDuration(250).withEndAction(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.GONE);
            }
        });
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
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

}
