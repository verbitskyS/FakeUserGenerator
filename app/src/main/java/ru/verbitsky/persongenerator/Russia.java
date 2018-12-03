package ru.verbitsky.persongenerator;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

import exceptions.CityNotFound;

/**
 * Класс, который генерирует данные для России (город, индекс, адрес, телефон)
 */

public class Russia implements Country{

    private String language;



    private String[] City = {"Новосибирск/Новосибирская область", "Москва/Москва", "Санкт-Петербург/Ленинградская область", "Омск/Омская область"
            , "Екатеринбург/Свердловская область", "Нижний Новгород/Нижегородская область", "Казань/Республика Татарстан"
            , "Челябинск/Челябинская область", "Самара/Самарская область", "Ростов-на-Дону/Ростовская область", "Уфа/Республика Башкортостан"
            , "Красноярск/Красноярский край", "Пермь/Пермский край", "Воронеж/Воронежская область", "Волгоград/Волгоградская область"
            , "Краснодар/Краснодарский край", "Тольятти/Самарская область", "Ижевск/Республика Удмуртия"
            , "Ульяновск/Ульяновская область", "Барнаул/Алтайский край", "Владивосток/Приморский край", "Ярославль/Ярославская область"
            , "Иркутск/Иркутская область", "Тюмень/Тюменская область", "Махачкала/Республика Дагестан"};


    private String[] CityEng = {"Novosibirsk/Novosibirsk Oblast", "Moscow/Moscow", "Saint-Petersburg/Leningrad Oblast", "Omsk/Omsk Oblast"
            , "Ekaterinburg/Sverdlovsk Oblast", "Nizhny Novgorod/Nizhny Novgorod Oblast", "Kazan/The Republic of Tatarstan"
            , "Chelyabinsk/Chelyabinsk Oblast", "Samara/Samara Oblast", "Rostov-on-Don/Rostov Oblast", "Ufa/The Republic of Bashkortostan"
            , "Krasnoyarsk/Krasnoyarsk Krai", "Perm/Perm Krai", "Voronezh/Voronezh Oblast", "Volgograd/Volgograd oblast"
            , "Krasnodar/Krasnodar Krai", "Togliatti/Samara Oblast", "Izhevsk/The Republic of Udmurtia"
            , "Ulyanovsk/Ulyanovsk Oblast", "Barnaul/Altai Krai", "Vladivostok/Primorsky Krai", "Yaroslavl/Yaroslavl Oblast"
            , "Irkutsk/Irkutsk Oblast", "Tyumen/Tyumen Oblast", "Makhachkala/The Republic of Dagestan"};




    private String[] Street = {"Ул. Ленина","Пр. Ленина","Пл. Ленина","Ул. Центральная","Ул. Лесная","Ул. Садовая","Ул. Совесткая",
            "Ул. Молодежная", "Ул. Школьная", "Ул. Набережная","Ул. Новая","Ул. Заречная","Ул. Зеленая","Дом Офицеров",
            "Ул. Пушкинская","Ул. Толстого","Ул. Гоголя","Ул. Покрышкина","Ул. Ахматовой","Ул. Красная",
            "Ул. Революционная","Ул. 1905 года","Ул. Тверская","Ул. Северная","Ул. Южная","Ул. Комсомольская",
            "Ул. Гагарина","Ул. Чкалова","Ул. Титова","Ул. Вокзальная","Пр. Свободы","Пл. Труда","Ул. Полевая","Ул. Озерная",
            "Ул. Рабочая","Ул. Береговая","Ул. Народная","Ул. Строителей","Ул. Юбилейная","Ул. Степная","Ул. Пролетарская",
            "Пр. Маркса", "Пр. Мира", "Ул. Мира", "Ул. Московская", "Ул. Петропавловская", "Ул. Зорге" };


    private String[] StreetEng = {"Lenina Street", "Lenina Avenue", "Lenina Square", "Centralnaya Street", "Lesnaya Street", "Sadovaya Street", "Sovestkaya Street",
            "Molodezhnaya Street", "Shkolnaya Street", "Naberezhnaya Street", "Novaya Street", "Zarechnaya Street", "Zelenaya Street", "Dom Oficerov Street",
            "Pushkinskaya Street", "Tolstogo Street", "Gogolya Street", "Pokryshkina Street", "Akhmatovoy Street", "Krasnaya Street",
            "Revolucionaya Street", "1905 goda Street", "Tverskaya Street", "Severnaya Street", "Yuzhnaya Street", "Komsomolskaya Street",
            "Gagarinskaya Street", "Chkalova Street", "Titova Street", "Vokzalnaya Street", "Svobody Avenue", "Truda Square", "Polevaya Street", "Ozernaya Street",
            "Rabochaya Street", "Beregovaya Street", "Narodnaya Street", "Stroiteley Street", "Yubileinaya Street", "Stepnaya Street", "Proletarskaya Street",
            "Marksa Avenue", "Mira Avenue", "Mira Street", "Moskovskaya Street", "Petropavlovskaya Street", "Zorge Street"};


    public Russia(String language){

        this.language = language;

    }



    @Override
    public String country() {


        if(language.equals("eng")){

            return "Russia";

        }else {
            return "Россия";
        }
    }


    /**
     * @return Возвращает cityInt-тый Город+Область из массива City или CityEng в зависимости от language
     */


    @Override
    public String city() {

        if(language.equals("eng")){

            MainActivity.cityInt =(int)(Math.random()*CityEng.length);

            return CityEng[MainActivity.cityInt];
        }else{

            return City[MainActivity.cityInt];
        }

    }


    /**
     * Генерирует индекс. Индексы соотвествуют сгенирорированному раннее городу.
     * @param city - название города
     * post - "почтовый" код города (привязан к данному городу)
     * code - 3 поседние цифры в индексе, которые генерируются {@link MainActivity#cityInt}
     * @return Возвращает почтый индекс (тип String)
     * @see Russia#city()
     */

    @Override
    public String postcode(String city)   throws CityNotFound {
        int post=984;
        int code = (int)(Math.random()*899)+100;


        switch (city){
            case "Новосибирск": post = 630;
                break;
            case "Москва": post = (int)(Math.random()*22)+108;
                break;
            case "Санкт-Петербург": post = (int)(Math.random()*9)+190;
                break;
            case "Омск": post = 644;
                break;
            case "Екатеринбург": post = 620;
                break;
            case "Нижний Новгород": post = 603;
                break;
            case "Казань": post = 420;
                break;
            case "Челябинск": post = 454;
                break;
            case "Самара": post = 443;
                break;
            case "Ростов-на-Дону": post = 344;
                break;
            case "Уфа": post = 450;
                break;
            case "Красноярск": post = 660;
                break;
            case "Пермь": post = 614;
                break;
            case "Воронеж": post = 394;
                break;
            case "Волгоград": post = 400;
                break;
            case "Краснодар": post = 350;
                break;
            case "Тольятти": post = 445;
                break;
            case "Ижевск": post = 426;
                break;
            case "Ульяновск": post = 432;
                break;
            case "Барнаул": post = 656;
                break;
            case "Владивосток": post = 690;
                break;
            case "Ярославль": post = 150;
                break;
            case "Иркутск": post = 664;
                break;
            case "Тюмень": post = 625;
                break;
            case "Махачкала": post = 367;
                break;

            default: throw new CityNotFound();
        }

        return String.valueOf(post) + String.valueOf(code);
    }



    /**
     * Возвращает физический адрес на русском языке, который состоит из улицы (streetInt-тая улица из списка самых популярных улиц России), квартиры и номера дома
     * @return название улицы, номер дома и квартиру через запятые
     */

    @Override
    public String adres() {

        if(language.equals("eng")) {

            MainActivity.homeInt = (int) (Math.random() * 50) + 1;
            MainActivity.flatInt = (int) (Math.random() * 70) + 1;
            MainActivity.streetInt = (int) (Math.random() * StreetEng.length);

            return String.valueOf(MainActivity.homeInt) + " " + StreetEng[MainActivity.streetInt] + ", Apt. " + String.valueOf(MainActivity.flatInt);

        }else {

            return Street[MainActivity.streetInt] + ", " + MainActivity.homeInt + ", кв. " + MainActivity.flatInt;
        }
    }



    /**
     * Генерирует номер мобильного телефона
     * @return Возвращает номер телефона в американском формате
     */



    @Override
    public String phone() {
        return "+7 9" + String.valueOf((int)(Math.random()*89)+10) + " " + String.valueOf((int)(Math.random()*899)+100)+ "-"+ String.valueOf((int)(Math.random()*89)+10)+ "-"+String.valueOf((int)(Math.random()*89)+10);

    }


    @Override
    public String[] allWords() {

        String[] splits;
        String[] cities=new String[City.length], subjects = new String[City.length];
        String[] citiesEng=new String[CityEng.length], subjectsEng = new String[CityEng.length];

        for(int i = 0; i<City.length; i++) {

            splits = City[i].split("/");
            cities[i] = splits[0];
            subjects[i] = splits[1];
            splits = CityEng[i].split("/");
            citiesEng[i] = splits[0];
            subjectsEng[i] = splits[1];

        }

        return ArrayUtils.concat(Street, cities, subjects, citiesEng, subjectsEng);
    }

    @Override
    public String getLocale(String s) {

        if(Arrays.asList(Street).contains(s)){

            return this.getClass().getName()+ " - Street";

        }else{

            if(Arrays.asList(StreetEng).contains(s)){

                return this.getClass().getName()+ " - StreetEng";
            }else{

                if (language=="eng"){

                    return this.getClass().getName()+ " - CityEng";
                }else{

                    return this.getClass().getName()+ " - City";
                }
            }



        }

    }

}
