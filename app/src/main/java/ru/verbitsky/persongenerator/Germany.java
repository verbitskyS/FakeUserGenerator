package ru.verbitsky.persongenerator;


import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

import exceptions.CityNotFound;

/**
 * Класс, который генерирует данные для Германии (город, адрес, индекс, телефон)
 */

public class Germany implements Country{


    private String[] Street = {"Ackerstraße","Bernauer Straße","Ebertstraße","Fasanerieallee","Frankfurter Allee","Friedrichstraße","Gollanczstraße",
            "Heerstraße", "Invalidenstraße", "Jüdenstraße","Kaiserdamm","Karl-Liebknecht-Straße","Karl-Marx-Allee","Kopenhagener Straße",
            "Kurfürstendamm","Legiendamm","Leipziger Straße","Leuschnerdamm","Majakowskiring","Mehringdamm",
            "Motzstraße","Niederkirchnerstraße","Ossietzkystraße","Prenzlauer Allee",
            "Rigaer Straße","Schönhauser Allee","Schwedter Straße","Siegesallee","Sonnenallee","Tauentzienstraße",
            "Turmstraße","Voßstraße","Warschauer Straße","Wilhelmstrasse"};


    private String[] City = {"Berlin/Berlin", "Hamburg/Hamburg", "Bavaria/Munich",
            "Cologne/North Rhine-Westphalia"
            , "Frankfurt am Main/Hesse", "Stuttgart/Baden-Württemberg", "Düsseldorf/North Rhine-Westphalia"
            , "Dortmund/North Rhine-Westphalia", "Essen/North Rhine-Westphalia", "Leipzig/Saxony", "Bremen/Bremen"
            , "Dresden/Saxony", "Hanover/Lower Saxony", "Nuremberg/Bavaria"
            , "Duisburg/North Rhine-Westphalia", "Bochum/North Rhine-Westphalia", "Wuppertal/North Rhine-Westphalia", "Bielefeld/North Rhine-Westphalia"
            , "Bonn/North Rhine-Westphalia", "Münster/North Rhine-Westphalia", "Karlsruhe/Baden-Württemberg", "Mannheim/Baden-Württemberg"
            , "Wiesbaden/Hesse"};



    @Override
    public String country() {
        return "Germany";
    }

    /**
     * Генерирует немецкий город+землю+почтовый индекс
     * @see Russia#city()
     * @see Russia#postcode(String)()
     * @return Возвращает город+землю+индекс
     */

    @Override
    public String city()  throws CityNotFound {


        String randomCity = City[(int)(Math.random()*City.length)];

        String[] splits = randomCity.split("/");


        int post = 0;
        int code = 0;


        switch (splits[0]) {
            case "Berlin":
                post = 10;
                code = (int) (Math.random() * 898) + 101;
                break;
            case "Hamburg":
                post = 20;
                code = (int) (Math.random() * 898) + 101;
                break;
            case "Munich":
                post = 80;
                code = (int) (Math.random() * 660) + 331;
                break;
            case "Cologne":
                post = 50;
                code = (int) (Math.random() * 310) + 677;
                break;
            case "Frankfurt am Main":
                post = 60;
                code = (int) (Math.random() * 290) + 308;
                break;
            case "Stuttgart":
                post = 70;
                code = (int) (Math.random() * 420) + 173;
                break;
            case "Düsseldorf":
                post = 40;
                code = (int) (Math.random() * 500) + 210;
                break;
            case "Dortmund":
                post = 44;
                code = (int) (Math.random() * 250) + 135;
                break;
            case "Essen":
                post = 45;
                code = (int) (Math.random() * 259) + 100;
                break;
            case "Leipzig":
                post = 04;
                code = (int) (Math.random() * 257) + 100;
                break;
            case "Bremen":
                post = 28;
                code = (int) (Math.random() * 679) + 100;
                break;
            case "Dresden":
                post = 01;
                code = (int) (Math.random() * 227) + 100;
                break;
            case "Hanover":
                post = 30;
                code = (int) (Math.random() * 500) + 159;
                break;
            case "Nuremberg":
                post = 90;
                code = (int) (Math.random() * 89) + 402;
                break;
            case "Duisburg":
                post = 470;
                code = (int) (Math.random() * 88) + 10;
                break;
            case "Bochum":
                post = 44;
                code = (int) (Math.random() * 193) + 701;
                break;
            case "Wuppertal":
                post = 42;
                code = (int) (Math.random() * 296) + 103;
                break;
            case "Bielefeld":
                post = 33;
                code = (int) (Math.random() * 238) + 501;
                break;
            case "Bonn":
                post = 53;
                code = (int) (Math.random() * 118) + 111;
                break;
            case "Münster":
                post = 48;
                code = (int) (Math.random() * 24) + 143;
                break;
            case "Karlsruhe":
                post = 76;
                code = (int) (Math.random() * 90) + 131;
                break;
            case "Mannheim":
                post = 68;
                code = (int) (Math.random() * 150) + 159;
                break;
            case "Wiesbaden":
                post = 65;
                code = (int) (Math.random() * 24) + 183;
                break;

            default: throw new CityNotFound();

        }

        String postcode = String.valueOf(post) + String.valueOf(code);


        return postcode + " " + randomCity;
    }




    /**
     * Аналогично как в
     * @see Russia#adres()
     * @return Возвращает случайную улицу (из списка самых популярных названий улиц в Германии) и случайный номер дома
     */

    @Override
    public String adres() {

        int homeInt = (int)(Math.random()*100)+1;

        return  Street[(int)(Math.random()*Street.length)]+ " " + String.valueOf(homeInt) ;
    }



    /**
     * Аналогично как в
     * @see Russia#phone()
     * @return Возвращает номер телефона в немецком формате
     */


    @Override
    public String phone() {
        return "+49 " + String.valueOf((int)(Math.random()*89)+10) + " " + String.valueOf((int)(Math.random()*8999)+1000)+ String.valueOf((int)(Math.random()*8999)+1000);
    }



    @Override
    public String postcode(String city) {

        return "";
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

            return this.getClass().getName()+ " - Street";

        }else{

            return this.getClass().getName()+ " - City";

        }

    }
}
