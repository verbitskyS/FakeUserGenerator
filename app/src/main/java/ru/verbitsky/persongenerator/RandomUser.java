package ru.verbitsky.persongenerator;

import android.util.Log;

public class RandomUser extends Data {



    Country country, countryEng;
    Origin origin, originEng;
    String[] splits;
    MaturalData maturalData;


    public RandomUser(String Countrynomer, String sex){

        this.CountryNomer = Countrynomer;
        this.sex = sex;
        maturalData = new MaturalData();

    }

    public void startRandom() {

        Log.d("MainActivity", "Генерируем нового пользователя, страна номер " + CountryNomer+", пол "+ sex);


        try {


            switch (CountryNomer) {
                case "0": //РОССИЯ

                    countryEng = new Russia("eng");
                    country = new Russia("ru");

                    originEng = new RussianOriginEng(sex);
                    origin = new RussianOrigin(sex);

                    FirstNameEng = originEng.name();

                    FatherNameEng = originEng.middleName();

                    LastNameEng = originEng.lastName();


                    CityEng = countryEng.city();


                    String[] splits = CityEng.split("/");

                    MainActivity.txCountryEng.setText(countryEng.country());
                    MainActivity.txCityEng.setText(splits[0]);
                    MainActivity.txSubjectEng.setText(splits[1]);


                    MainActivity.txNameEng.setText(FirstNameEng);
                    MainActivity.txLastNameEng.setText(LastNameEng);
                    MainActivity.txFatherNameEng.setText(FatherNameEng);


                    MainActivity.txAdresEng.setText(countryEng.adres());



                    break;


                case "1": //США

                    country = new Usa();
                    origin = new UsaOrigin(sex);

                    break;


                case "2":

                    country = new Uk();
                    origin = new UkOrigin(sex);

                    break;


                case "3": //Германия

                    country = new Germany();
                    origin = new GermanyOrigin(sex);

                    break;


                case "4": //Франция

                    country = new France();
                    origin = new FranceOrigin(sex);

                    break;

            }



            Country = country.country();
            MainActivity.txCountry.setText(Country);

            FirstName = origin.name();
            LastName = origin.lastName();
            FatherName = origin.middleName();


            City = country.city();

            if (City.contains("/")) {

                splits = City.split("/");

                MainActivity.txSubject.setText(splits[1]);
                MainActivity.txCity.setText(splits[0]);

            }else{

                MainActivity.txCity.setText(City);

            }

            MainActivity.txPhone.setText(country.phone());

            MainActivity.txAdres.setText(country.adres());


            if(!CountryNomer.equals("0")){

                MainActivity.txName.setTextSize(25);

                MainActivity.txName.setText(FirstName + " " + FatherName + " " + LastName);


            }else{

                MainActivity.txName.setText(FirstName);
                MainActivity.txLastName.setText(LastName);
                MainActivity.txFatherName.setText(FatherName);
                MainActivity.txPostCode.setText(country.postcode(splits[0]));

            }


            Birthday = maturalData.Birhday();

            MainActivity.txAge.setText(Birthday);

            String[] splits = Birthday.split(", ");






            switch (CountryNomer) {
                case "0":
                    MainActivity.txEmail.setText(maturalData.Email(LastNameEng, FirstNameEng, splits[0]));
                    MainActivity.txLogin.setText(maturalData.Login(LastNameEng, FirstNameEng));
                    break;

                case "2":
                    String[] splitsName = FirstName.split(" ");
                    MainActivity.txEmail.setText(maturalData.Email(LastName, splitsName[1], splits[0]));
                    MainActivity.txLogin.setText(maturalData.Login(LastName, splitsName[1]));
                    break;

                default:
                    MainActivity.txEmail.setText(maturalData.Email(LastName, FirstName, splits[0]));
                    MainActivity.txLogin.setText(maturalData.Login(LastName, FirstName));
                    break;

            }

            MainActivity.txPassword.setText(maturalData.Password());


            if (sex.equals("male")) {



                if (Integer.parseInt(splits[1]) < 30) {
                    photoString = String.valueOf(photo20Man[(int) (Math.random() * (photo20Man.length))]);
                } else {
                    if (Integer.parseInt(splits[1]) < 40) {
                        photoString = String.valueOf(photo30Man[(int) (Math.random() * (photo30Man.length))]);
                    } else {
                        photoString = String.valueOf(photo40Man[(int) (Math.random() * (photo40Man.length))]);
                    }
                }



            } else {


                if (Integer.parseInt(splits[1]) < 30) {
                    photoString = String.valueOf(photo20Woman[(int) (Math.random() * (photo20Woman.length))]);
                } else {
                    if (Integer.parseInt(splits[1]) < 40) {
                        photoString = String.valueOf(photo30Woman[(int) (Math.random() * (photo30Woman.length))]);
                    } else {
                        photoString = String.valueOf(photo40Woman[(int) (Math.random() * (photo40Woman.length))]);
                    }
                }

            }

            MainActivity.txCard.setText(maturalData.Paymants());
            MainActivity.txCVC.setText(maturalData.PaymantsCVC());
            MainActivity.txDateCard.setText(maturalData.PaymantsDate());





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


    }catch(Exception e){


        Log.e("RandomUser.class", "Ошибка в генерации: "+e.getMessage(), e);


        }

    }

}
