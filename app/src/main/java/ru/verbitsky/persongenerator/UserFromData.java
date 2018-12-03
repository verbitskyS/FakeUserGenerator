package ru.verbitsky.persongenerator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class UserFromData extends Data{


    ContentValues cv;
    SQLiteDatabase db;
    int position;
    Cursor cursor;
    DBHelper dbHelper;

    Data data;



    int idColIndex, idStolbcaColIndex, nameColIndex, firstnameColIndex, lastnameColIndex, fathernameColIndex, nomerPhotoColIndex, birthdayColIndex, subjectColIndex, countryColIndex,
            cityColIndex, addressColIndex, postcodeColIndex, numberPhoneColIndex, loginColIndex, emailColIndex, passwordColIndex, cardColIndex, cvc2ColIndex, cardDateColIndex,
            firstnameEngColIndex, lastnameEngColIndex, fathernameEngColIndex,subjectEngColIndex, cityEngColIndex, addressEngColIndex, countryNomerColIndex;



    public UserFromData(DBHelper dbHelper, Data data){

        this.data = data;
        this.dbHelper = dbHelper;
        db =  this.dbHelper.getWritableDatabase();

    }


    public void downloadUser(int position){

        Log.d("MainActivity", "Мы перешли в эту активити через активити 'сохраненные пользователи', получаем данные из базы данных");


        try {

            cursor = db.query("mytable", null, null, null, null, null, null);

            ////ЕСЛИ POSITION = -1 ТО РАНДОМ, ЕСЛИ НЕТ ТО ЭТО НОМЕР СОХРАНЕННОГО ПОЛЬЗОВАТЕЛЯ


            cursor.moveToPosition(position);
            idColIndex = cursor.getColumnIndex("id");
            idStolbcaColIndex = cursor.getColumnIndex("idStolbca");
            countryNomerColIndex = cursor.getColumnIndex("countryNomer");
            nameColIndex = cursor.getColumnIndex("name");
            firstnameColIndex = cursor.getColumnIndex("firstName");
            lastnameColIndex = cursor.getColumnIndex("secondName");
            fathernameColIndex = cursor.getColumnIndex("fatherName");
            firstnameEngColIndex = cursor.getColumnIndex("firstNameEng");
            lastnameEngColIndex = cursor.getColumnIndex("secondNameEng");
            fathernameEngColIndex = cursor.getColumnIndex("fatherNameEng");
            nomerPhotoColIndex = cursor.getColumnIndex("photoName");
            birthdayColIndex = cursor.getColumnIndex("birthday");
            countryColIndex = cursor.getColumnIndex("country");
            subjectColIndex = cursor.getColumnIndex("subject");
            cityColIndex = cursor.getColumnIndex("city");
            addressColIndex = cursor.getColumnIndex("address");
            subjectEngColIndex = cursor.getColumnIndex("subjectEng");
            cityEngColIndex = cursor.getColumnIndex("cityEng");
            addressEngColIndex = cursor.getColumnIndex("addressEng");
            postcodeColIndex = cursor.getColumnIndex("postcode");
            numberPhoneColIndex = cursor.getColumnIndex("numberPhone");
            loginColIndex = cursor.getColumnIndex("login");
            emailColIndex = cursor.getColumnIndex("email");
            passwordColIndex = cursor.getColumnIndex("password");
            cardColIndex = cursor.getColumnIndex("card");
            cvc2ColIndex = cursor.getColumnIndex("cvc2");
            cardDateColIndex = cursor.getColumnIndex("cardDate");

            idStolbca = cursor.getString(idStolbcaColIndex);
        /*} else {
            Log.d("MainActivity", "Генерируем случайного пользователя");

        }*/


            CountryNomer = cursor.getString(countryNomerColIndex);
            FirstName = cursor.getString(firstnameColIndex);
            LastName = cursor.getString(lastnameColIndex);
            FatherName = cursor.getString(fathernameColIndex);


            FirstNameEng = cursor.getString(firstnameEngColIndex);
            LastNameEng = cursor.getString(lastnameEngColIndex);
            FatherNameEng = cursor.getString(fathernameEngColIndex);


            if (CountryNomer.equals("0")) {
                MainActivity.txName.setText(FirstName);
                MainActivity.txNameEng.setText(FirstNameEng);
                MainActivity.txLastName.setText(LastName);
                MainActivity.txLastNameEng.setText(LastNameEng);
                MainActivity.txFatherName.setText(FatherName);
                MainActivity.txFatherNameEng.setText(FatherNameEng);
            } else {

                MainActivity.txName.setText(FirstName + " " + FatherName + " " + LastName);
                MainActivity.txName.setTextSize(25);

            }

            MainActivity.txCountry.setText(cursor.getString(countryColIndex));
            MainActivity.txSubject.setText(cursor.getString(subjectColIndex));
            MainActivity.txSubjectEng.setText(cursor.getString(subjectEngColIndex));
            MainActivity.txCity.setText(cursor.getString(cityColIndex));
            MainActivity.txCityEng.setText(cursor.getString(cityEngColIndex));
            MainActivity.txAdres.setText(cursor.getString(addressColIndex));
            MainActivity.txAdresEng.setText(cursor.getString(addressEngColIndex));
            MainActivity.txPostCode.setText(cursor.getString(postcodeColIndex));

            MainActivity.txPhone.setText(cursor.getString(numberPhoneColIndex));


            photoString = cursor.getString(nomerPhotoColIndex);


            Birthday = cursor.getString(birthdayColIndex);


            MainActivity.txAge.setText(Birthday);

            if (StartDisplay.languageFlag == 0) {

                MainActivity.txAge.append(" years old");


            } else {


                switch (Birthday.toString().substring(Birthday.toString().length() - 1)) {
                    case "1":
                        MainActivity.txAge.append(" год");
                        break;
                    case "2":
                        MainActivity.txAge.append(" года");
                        break;
                    case "3":
                        MainActivity.txAge.append(" года");
                        break;
                    case "4":
                        MainActivity.txAge.append(" года");
                        break;
                    default:
                        MainActivity.txAge.append(" лет");
                        break;

                }
            }


            MainActivity.txLogin.setText(cursor.getString(loginColIndex));
            MainActivity.txEmail.setText(cursor.getString(emailColIndex));
            MainActivity.txPassword.setText(cursor.getString(passwordColIndex));

            MainActivity.txCard.setText(cursor.getString(cardColIndex));
            MainActivity.txCVC.setText(cursor.getString(cvc2ColIndex));
            MainActivity.txDateCard.setText(cursor.getString(cardDateColIndex));

            dbHelper.close();
            cursor.close();

        }catch (SQLiteException sq){

            Log.e("База данных", "ошибка с загрузкой данных из базы данных!", sq);

        }finally {
            dbHelper.close();
            cursor.close();
        }



    }



    public void saveUser(){




        Log.d("База данных", "Сохраянем нового польхователя");

        try {

        db = dbHelper.getWritableDatabase();

        idStolbca = String.valueOf((int) (Math.random() * 100000000));
        cv = new ContentValues();

        cv.put("idStolbca", idStolbca);



        if (data.CountryNomer.equals("0")) {
            cv.put("name", data.FirstName + " " + data.LastName);
            cv.put("nameEng", data.FirstNameEng + " " + data.LastNameEng);
        } else {
            cv.put("name", MainActivity.txName.getText().toString());
        }

        cv.put("sex", data.sex);
        cv.put("firstName", data.FirstName);
        cv.put("secondName", data.LastName);
        cv.put("fatherName", data.FatherName);
        cv.put("firstNameEng", data.FirstNameEng);
        cv.put("secondNameEng", data.LastNameEng);
        cv.put("fatherNameEng", data.FatherNameEng);
        cv.put("birthday", data.Birthday);
        cv.put("country", MainActivity.txCountry.getText().toString());
        cv.put("countryNomer", data.CountryNomer);
        cv.put("subject", MainActivity.txSubject.getText().toString());
        cv.put("city", MainActivity.txCity.getText().toString());
        cv.put("address", MainActivity.txAdres.getText().toString());
        cv.put("countryEng", MainActivity.txCountryEng.getText().toString());
        cv.put("subjectEng", MainActivity.txSubjectEng.getText().toString());
        cv.put("cityEng", MainActivity.txCityEng.getText().toString());
        cv.put("addressEng", MainActivity.txAdresEng.getText().toString());
        cv.put("postcode", MainActivity.txPostCode.getText().toString());
        cv.put("numberPhone", MainActivity.txPhone.getText().toString());
        cv.put("login", MainActivity.txLogin.getText().toString());
        cv.put("email", MainActivity.txEmail.getText().toString());
        cv.put("password", MainActivity.txPassword.getText().toString());
        cv.put("card", MainActivity.txCard.getText().toString());
        cv.put("cvc2", MainActivity.txCVC.getText().toString());
        cv.put("cardDate", MainActivity.txDateCard.getText().toString());
        cv.put("photoName", data.photoString);
        db.insert("mytable", null, cv);
        dbHelper.close();

    }catch (SQLiteException sq){

        Log.e("База данных", "ошибка с сохранением данных в базу данных!", sq);

    }finally {

            dbHelper.close();
        }

    }


    public void deleteUser(){

        Log.d("База данных", "Удаляем нового пользователя");

        try {


            db = dbHelper.getWritableDatabase();

            int delCount = db.delete("mytable", "idStolbca=" + idStolbca, null);

            dbHelper.close();

        }catch (SQLiteException sq){

            Log.e("База данных", "ошибка с удалением данных из базы данных!", sq);

        }finally {

            dbHelper.close();
        }

    }


}
