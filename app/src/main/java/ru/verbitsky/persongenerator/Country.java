package ru.verbitsky.persongenerator;

import exceptions.CityNotFound;

public interface Country extends ParentInterface{


    String country();
    String city() throws CityNotFound;
    String postcode(String city) throws CityNotFound;
    String adres();
    String phone();

}
