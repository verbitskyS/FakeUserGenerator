package ru.verbitsky.persongenerator;


import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

import exceptions.CityNotFound;

/**
 * Класс, который генерирует данные для США (город, адрес, индекс, телефон)
 */


public class Usa implements Country{



    private String[] City = {"New York, NY", "Los Angeles, CA", "Chicago, IL", "Houston, TX"
            , "Philadelphia, PA", "Phoenix, AZ", "San Antonio, TX"
            , "San Diego, CA", "Dallas, TX", "San Jose, CA", "Austin, TX"
            , "Indianapolis, IN", "Jacksonville, FL", "San Francisco, CA", "Columbus, OH"
            , "Charlotte, NC", "Fort Worth, TX", "Detroit, MI"
            , "El Paso, TX", "Memphis, TN", "Seattle, WA", "Denver, CO"
            , "Washington, D.C.", "Boston, MA", "Nashville, TN"};

    private String[] Street = {"First Street","Second Street","Third Street","Fourth Street","Fifth Street","Sixth Street","Seventh Street",
            "Ninth Street", "Main Street", "Park Street","Oak Street","Pine Street","Maple Street","Cedar Street",
            "Eighth Street","Elm Street","View Street","Washington Street","Lake Street","Hill Street",
            "Walnut Street","Sunset Street","Lincoln Street","Jackson Street","Church Street","River Street",
            "Willow Street","Jefferson Street","Center Street","North Street","Lakeview Street","Ridge Street","Hickory Street","Adams Street",
            "Cherry Street","Highland Street","Johnson Street","South Street","Dogwood Street","West Street","Chestnut Street",
            "Wilson Street", "Forest Street", "Madison Street", "Meadow Street", "Broken Way", "Ruckman Road", "Black Oak Hollow Road",
            "Green Gate Lane", "Hartway Street", "Rose Avenue", "Willis Avenue", "Sycamore Circle", "Morris Street", "Platinum Drive",
            "Biddie Lane", "Victoria Court", "County Line Road", "Emerson Road", "Deercove Drive", "Pringle Drive", "Adonais Way",
            "Hall Valley Drive", "Elk Creek Road", "Cardinal Lane", "Oak Ridge Drive", "Kerry Way", "Doe Meadow Drive",
            "Kennedy Court", "Williams Avenue", "Simpson Square", "Cook Hill Road", "College View", "Green Hill Road", "Lilac Lane"};



    @Override
    public String country() {
        return "United States";
    }

    /**
     * Аналогично как в
     * @see Germany#city()
     * @return Возвращает cityInt-тый Город+Штат из массива City + почтовый индекс
     */


    @Override
    public String city() throws CityNotFound {


        String randomCity = City[(int)(Math.random()*City.length)];


        int post = 0;
        int code =0;


        switch (randomCity){
            case "New York, NY": post = 10;
                code = (int)(Math.random()*184)+101;
                break;
            case "Los Angeles, CA":  post = 900;
                code = (int)(Math.random()*86)+10;
                break;
            case "Chicago, IL": post = 606;
                code = (int)(Math.random()*88)+10;
                break;
            case "Houston, TX": post = 770;
                code = (int)(Math.random()*88)+10;
                break;
            case "Philadelphia, PA": post = 19;
                code = (int)(Math.random()*198)+101;
                break;
            case "Phoenix, AZ":post = 850;
                code = (int)(Math.random()*87)+10;
                break;
            case "San Antonio, TX": post = 782;
                code = (int)(Math.random()*88)+10;
                break;
            case "San Diego, CA": post = 921;
                code = (int)(Math.random()*88)+10;
                break;
            case "Dallas, TX": post = 752;
                code = (int)(Math.random()*50)+10;
                break;
            case "San Jose, CA": post = 951;
                code = (int)(Math.random()*85)+10;
                break;
            case "Austin, TX": post = 787;
                code = (int)(Math.random()*68)+10;
                break;
            case "Indianapolis, IN": post = 462;
                code = (int)(Math.random()*87)+10;
                break;
            case "Jacksonville, FL": post = 322;
                code = (int)(Math.random()*49)+10;
                break;
            case "San Francisco, CA":post = 941;
                code = (int)(Math.random()*52)+12;
                break;
            case "Columbus, OH": post = 432;
                code = (int)(Math.random()*80)+10;
                break;
            case "Charlotte, NC": post = 282;
                code = (int)(Math.random()*88)+10;
                break;
            case "Fort Worth, TX": post = 761;
                code = (int)(Math.random()*88)+10;
                break;
            case "Detroit, MI": post = 482;
                code = (int)(Math.random()*68)+10;
                break;
            case "El Paso, TX": post = 799;
                code = (int)(Math.random()*88)+10;
                break;
            case "Memphis, TN": post = 381;
                code = (int)(Math.random()*86)+10;
                break;
            case "Seattle, WA":post = 981;
                code = (int)(Math.random()*88)+10;
                break;
            case "Denver, CO": post = 802;
                code = (int)(Math.random()*88)+10;
                break;
            case "Washington, D.C.": post = 200;
                code = (int)(Math.random()*88)+10;
                break;
            case "Boston, MA": post = 021;
                code = (int)(Math.random()*81)+10;
                break;
            case "Nashville, TN": post = 372;
                code = (int)(Math.random()*39)+10;
                break;


                default: throw new CityNotFound();

        }

        String postcode = String.valueOf(post) + String.valueOf(code);



        return randomCity + " " + postcode;
    }




    /**
     * Аналогично как в
     * @see Russia#adres() ()
     * @return Возвращает случайную улицу (из списка самых популярных названий улиц в США) и случайный номер дома
     */

    @Override
    public String adres() {

        int homeInt = (int)(Math.random()*2000) +1;

        return  String.valueOf(homeInt)+ " " + Street[(int)(Math.random()*Street.length)];
    }





    /**
     * Аналогично как в
     * @see Russia#phone()
     * @return Возвращает номер телефона в американском формате
     */


    @Override
    public String phone() {
        return "+1 " + String.valueOf((int)(Math.random()*899)+100) + " " + String.valueOf((int)(Math.random()*899)+100)+ "-"+ String.valueOf((int)(Math.random()*8999)+1000);
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
