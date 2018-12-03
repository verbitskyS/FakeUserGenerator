package ru.verbitsky.persongenerator;


import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

import exceptions.CityNotFound;

/**
 * Класс, который генерирует данные для Франции (город, адрес, индекс, телефон)
 */


public class France implements Country {



    private String[] City = {"Paris", "Marseille", "Lyon",
            "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux", "Lille", "Rennes",
            "Reims", "Le Havre", "Saint-Étienne", "Toulon", "Grenoble", "Dijon", "Nîmes",
            "Angers", "Villeurbanne", "Le Mans", "Saint-Denis", "Aix-en-Provence"};


    private String[] Street = {"Rue de l’Église","Place de l’Église","Grande Rue","Rue du Moulin","Place de la Mairie","Rue du Château","Rue des Écoles",
            "Rue de la Gare", "Rue de la Mairie", "Rue Principale","Rue du Stade","Rue de la Fontaine","Rue Pasteur","Rue des Jardins",
            "Rue Victor-Hugo"};


    @Override
    public String country() {
        return "France";
    }


    /**
     * Аналогично как в
     * @see Germany#city()
     * @return Возвращает французский город и почтоый индекс
     */

    @Override
    public String city()  throws CityNotFound {

        String randomCity = City[(int)(Math.random()*City.length)];

        int post = 0;
        int code = 0;


        switch (randomCity) {
            case "Paris":
                post = 7506;
                code = (int) (Math.random() * 9);
                break;
            case "Marseille":
                post = 1300;
                code = (int) (Math.random() * 9)+1;
                break;
            case "Lyon":
                post = 6900;
                code = (int) (Math.random() * 9) + 1;
                break;
            case "Toulouse":
                post = 3100;
                code = (int) (Math.random() * 9) + 1;
                break;
            case "Nice":
                post = 06;
                code = 100*((int) (Math.random() * 3)+1);
                break;
            case "Nantes":
                post = 44;
                code = 100*((int) (Math.random() * 3)+1);
                break;
            case "Strasbourg":
                post = 67;
                code = 100*((int) (Math.random() * 2)+1);
                break;
            case "Montpellier":
                post = 340;
                code = 10*((int) (Math.random() * 2)+7);
                break;
            case "Bordeaux":
                post = 33;
                code = 100*((int) (Math.random() * 3)+1);
                break;
            case "Lille":
                post = 5900;
                code = 0;
                break;
            case "Rennes":
                post = 3500;
                code = 0;
                break;
            case "Reims":
                post = 5110;
                code = 0;
                break;
            case "Le Havre":
                post = 7662;
                code = 0;
                break;
            case "Saint-Étienne":
                post = 4210;
                code = 0;
                break;
            case "Toulon":
                post = 8320;
                code = 0;
                break;
            case "Grenoble":
                post = 3810;
                code = 0;
                break;
            case "Dijon":
                post = 2100;
                code = 0;
                break;
            case "Nîmes":
                post = 3090;
                code = 0;
                break;
            case "Angers":
                post = 4910;
                code = 0;
                break;
            case "Villeurbanne":
                post = 6910;
                code = 0;
                break;
            case "Le Mans":
                post = 7210;
                code = 0;
                break;
            case "Saint-Denis":
                post = 9320;
                code = 6;
                break;
            case "Aix-en-Provence":
                post = 1309;
                code = 8;
                break;

                default: throw new CityNotFound();

        }

        String postcode = String.valueOf(post) + String.valueOf(code);


        return postcode + " "+randomCity;

    }





    /**
     * Аналогично как в
     * @see Russia#adres()
     * @return Возвращает случайную улицу (из списка самых популярных названий улиц во Франции) и случайный номер дома
     */


    @Override
    public String adres() {


        int homeInt = (int)(Math.random()*100)+1;

        return  String.valueOf(homeInt)+ " " + Street[(int)(Math.random()*Street.length)] ;
    }



    /**
     * Аналогично как в
     * @see Russia#phone()
     * @return Возвращает номер телефона во французском формате
     */

    @Override
    public String phone() {
        return "+33 06 " + String.valueOf((int)(Math.random()*89)+10) + " " + String.valueOf((int)(Math.random()*89)+10) + " "+ String.valueOf((int)(Math.random()*89)+10) + " "+ String.valueOf((int)(Math.random()*89)+10);

    }


    @Override
    public String postcode(String city) {

        return "";
    }


    @Override
    public String[] allWords() {

        return ArrayUtils.concat(Street, City);
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
