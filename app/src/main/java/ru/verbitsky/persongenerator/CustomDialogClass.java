package ru.verbitsky.persongenerator;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Класс для создания кастомного диалога (просьба оставить отзыв о приложении)
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;

    /**
     * Конструктор который передает активити роительскому классу
     */

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    /**
     * Создает диалог с 2 кнопками
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    /**
     * Переопределенный метод из интерфейса android.view.View.OnClickListener, который обрабатывает нажатия кнопок
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
               cancel();
                break;
            case R.id.btn_no:
                onClickRateThisApp();

                break;
            default:
                break;
        }
        dismiss();
    }

    /**
     * Метод, который перенаправляет на страницу приложения в GooglePlay, если это возможно
     */


    public void onClickRateThisApp() {
        Uri uri = Uri.parse("market://details?id=ru.verbitsky.persongenerator");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);



        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            getContext().startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=ru.verbitsky.persongenerator")));
        }


    }
}