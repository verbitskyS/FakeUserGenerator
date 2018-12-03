package ru.verbitsky.persongenerator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Класс для работы с базой данных SQLite для сохранения пользователей
 */


class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "myDB";
    final static int DATABASE_VERSION = 1;


    final static String data = "create table mytable ("
            + "id integer primary key autoincrement,"
            + "idStolbca text,"
            + "sex text,"
            + "name text,"
            + "nameEng text,"
            + "firstName text,"
            + "secondName text,"
            + "fatherName text,"
            + "firstNameEng text,"
            + "secondNameEng text,"
            + "fatherNameEng text,"
            + "birthday text,"
            + "country text,"
            + "countryNomer text,"
            + "subject text,"
            + "city text,"
            + "address text,"
            + "countryEng text,"
            + "subjectEng text,"
            + "cityEng text,"
            + "addressEng text,"
            + "postcode text,"
            + "numberPhone text,"
            + "login text,"
            + "email text,"
            + "password text,"
            + "card text,"
            + "cvc2 text,"
            + "cardDate text,"
            + "photoName text" +  ");";

    /**
     * Конструктор, который передает данные в родительский класс
     */

    public DBHelper(Context context) {
        super(context, DBName, null, 1);

    }


    /**
     * Переопределенный метод, который создает базу данных с полями
     */

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(data);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w("DBHelper", "Обновление базы данных с версии " + oldVersion
                + " до "
                + newVersion + " и удаление старых данных");

        db.execSQL("DROP TABLE IF EXISTS '" + DBName + "'");
        onCreate(db);

    }
}