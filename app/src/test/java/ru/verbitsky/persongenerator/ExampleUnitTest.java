package ru.verbitsky.persongenerator;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import exceptions.CityNotFound;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    Country country, countryRussia;
    Origin origin, originRussia;
    ArrayList<Country> listCountry;
    ArrayList<Origin> listOrigin;

    @Before
    public void start() throws Exception{

        originRussia = new RussianOrigin("male");
        countryRussia = new Russia("ru");

        listOrigin = new ArrayList<>();
        listCountry = new ArrayList<>();

        listCountry.add(new Russia("ru"));
        listCountry.add(new Russia("eng"));
        listCountry.add(new Uk());
        listCountry.add(new Usa());
        listCountry.add(new France());
        listCountry.add(new Germany());
        listOrigin.add(new RussianOrigin("male"));
        listOrigin.add(new RussianOrigin("female"));
        listOrigin.add(new UsaOrigin("male"));
        listOrigin.add(new UsaOrigin("female"));
        listOrigin.add(new UkOrigin("male"));
        listOrigin.add(new UkOrigin("female"));
        listOrigin.add(new FranceOrigin("male"));
        listOrigin.add(new FranceOrigin("female"));
        listOrigin.add(new GermanyOrigin("male"));
        listOrigin.add(new GermanyOrigin("female"));


    }


    /**
     * Проверяем генерацию имени (как быстро сгенерируется нужное имя)
     */

    @Test
    public void meetsName() {


       ArrayList<String> list = new ArrayList<>();
       String name, nameS = "Сергей";


        for(int i = 0; i<100; i++){

            name = originRussia.name();
            list.add(name);
            if(name == nameS){
                System.out.println("Нужное имя сгенерировалось за " + i+" иттераций");

            }

        }

        assertThat(list, hasItem(nameS));



    }

    /**
     * Проверяем все строки (имена, фамилии, города...) на случайные пробелы в начале и в конце строки
     */

    @Test
    public void probel() {

        int kolvoSluchainihProbelov = 0;


        for (Origin org : listOrigin) {

            for(int i=0; i<org.allWords().length; i++) {

                if (org.allWords()[i].charAt(0) == ' ') {
                    kolvoSluchainihProbelov++;
                    System.out.println(org.allWords()[i] + " at " + org.getLocale(org.allWords()[i]));
                }

            }

        }

        for (Country cntry : listCountry) {

            for(int i=0; i<cntry.allWords().length; i++) {

                if (cntry.allWords()[i].charAt(0) == ' ') {
                    kolvoSluchainihProbelov++;
                    System.out.println(cntry.allWords()[i] + " at " + cntry.getLocale(cntry.allWords()[i]));
                }

            }

        }


        assertEquals(0, kolvoSluchainihProbelov);

    }

    /**
     * Выводим несколько новосибирских индексов и российских номеров телефона
     */

    @Test
    public void postcodesAndNumbers() throws CityNotFound {


        ArrayList<String> postCodes = new ArrayList<>();
        ArrayList<String> phones = new ArrayList<>();

        for(int i = 0; i<10; i++){

            postCodes.add(countryRussia.postcode("Новосибирcк"));
            phones.add(countryRussia.phone());

        }

        System.out.println(postCodes +"\n"+phones);


        assertEquals(2, 1+1);


    }

}