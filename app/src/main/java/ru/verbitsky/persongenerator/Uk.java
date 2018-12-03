package ru.verbitsky.persongenerator;


import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

/**
 * Класс, который генерирует данные для Великобритании (город, адрес, телефон)
 */

public class Uk implements Country {





    private String[] City = {"London/County of London", "Birmingham/West Midlands", "City of Leeds/West Yorkshire",
            "Sheffield/South Yorkshire"
            , "Bradford/West Yorkshire", "Manchester/Greater Manchester", "Liverpool/Merseyside"
            , "Wakefield/West Yorkshire", "Coventry/West Midlands", "Leicester/Leicestershire", "Nottingham/Nottinghamshire"
            , "Sunderland/Tyne and Wear", "Newcastle upon Tyne/Tyne and Wear", "Kingston upon Hull/East Riding of Yorkshire"
            , "Belfast/Antrim", "Derry/Londonderry", "Glasgow/Glasgow City", "Edinburgh/City of Edinburgh"
            , "Aiberdeen/Aberdeen City", "Dundee/Dundee City", "Cardiff/Glamorgan", "Swansea/Glamorgan"
            , "Newport/Monmouthshire"};

    private String[] Street = {"High Street","Church Lane","Station Road","Church Street","Mill Lane","Church Road","Green Lane",
            "School Lane", "Main Street", "New Road","Back Lane","Chapel Lane","Park Road","The Green",
            "The Street","Orchard Close","The Crescent","Manor Road","The Avenue","Park Lane",
            "Chapel Street","West Street","Queen Street","Victoria Road","King Street","North Street",
            "New Street","Sandy Lane","The Close","George Street",  "Wilson Street", "Forest Street", "Madison Street", "Meadow Street", "Broken Way", "Ruckman Road", "Black Oak Hollow Road",
            "Green Gate Lane", "Hartway Street", "Rose Avenue", "Willis Avenue", "Sycamore Circle", "Morris Street", "Platinum Drive",
            "Biddie Lane", "Victoria Court", "County Line Road", "Emerson Road", "Deercove Drive", "Pringle Drive", "Adonais Way",
            "Hall Valley Drive", "Elk Creek Road", "Cardinal Lane", "Oak Ridge Drive", "Kerry Way", "Doe Meadow Drive",
            "Kennedy Court", "Williams Avenue", "Simpson Square", "Cook Hill Road", "College View", "Green Hill Road", "Lilac Lane"};



    private int randomUkCity = 0;


    /**
     * Сначала генерируем страну из 4-ех, запоминаем и только потом город
     * @see Russia#city()
     * @return Возвращает страну в Великобритании
     */


    @Override
    public String country() {

        String s;

        randomUkCity = (int)(Math.random()*City.length);

        if(randomUkCity<14){
            s = "England";
        }else{
            if(randomUkCity<16){
                s = "Ireland";
            }else{
                if(randomUkCity<20){
                   s = "Scotland";
                }else{
                    s = "Wales";
                }
            }
        }

        return "United Kingdom, " + s;
    }




    /**
     * Аналогично как в
     * @see Russia#city()
     * @return Возвращает cityInt-тый Город+Область из массива City
     */



    @Override
    public String city() {


        return City[randomUkCity];
    }



    /**
     * Аналогично как в
     * @see Russia#adres()
     * @return Возвращает homeInt-тый дом и случайную улицу из списка популярных улиц Великобритании через пробел (таков формат адреса в англоязычных странах)
     */


    @Override
    public String adres() {

        int homeInt = (int)(Math.random()*200)+1;

        return  String.valueOf(homeInt)+ " " + Street[(int)(Math.random()*Street.length)];
    }



    /**
     * Аналогично как в
     * @see Russia#phone()
     * @return Возвращает номер телефона. "+44 7" - с таких цифр начинается любой мобильный номер телефона в Великобритании, остальные цифры в номере- случайные
     */

    @Override
    public String phone() {
        return "+44 7" + String.valueOf((int)(Math.random()*10)) + " " + String.valueOf((int)(Math.random()*8999)+1000)+ " "+ String.valueOf((int)(Math.random()*8999)+1000);
    }





    /**
     * Пока еще не реализовал генерацию почтового индекса для Великобритании
     */
    @Override
    public String postcode(String city) {
        return null;
    }



    @Override
    public String[] allWords() {

        String[] splits;
        String[] cities=new String[City.length], subjects = new String[City.length];

        for(int i = 0; i<City.length; i++) {

            splits = City[i].split("/");
            cities[i] = splits[0];
            subjects[i] = splits[1];

        }

        return ArrayUtils.concat(Street, cities, subjects);
    }

    @Override
    public String getLocale(String s) {

        if(Arrays.asList(Street).contains(s)){

            return  this.getClass().getName()+ " - Street";

        }else{

            return  this.getClass().getName()+ " - City";

        }

    }



}
