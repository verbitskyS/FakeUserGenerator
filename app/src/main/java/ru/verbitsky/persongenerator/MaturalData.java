package ru.verbitsky.persongenerator;

import java.util.Calendar;

/**
 * Класс для генерации возраста, пароль от эл. почты, адрес посчты, логин, данные дебетовой карты (данные, независящие от выбранной страны)
 */

public class MaturalData {





    static int[] digits;
    static String card;

    /**
     * Метод для генерации даты рождения (от 1968 года до 1998)
     * StartDisplay.ageFlag - переменная из настроек генерации возраста
     * @see StartDisplay#ageFlag
     * @return Возвращает дату рождения и возраст
     */

    public String Birhday()
    {
        String day;
        String mount = String.valueOf((int)(Math.random()*12+1));
        if (Integer.parseInt(mount)==2) {
            day = String.valueOf((int) (Math.random() * 28 + 1));
        }else{
            day = String.valueOf((int) (Math.random() * 30 + 1));
        }

        String year="";

        switch (StartDisplay.ageFlag){

            case 0: year = String.valueOf((int) (Math.random()*30 + 1968));
            break;
            case 1: year = String.valueOf((int) (Math.random()*10 + 1988));
            break;
            case 2: year = String.valueOf((int) (Math.random()*10 + 1978));
                break;
            case 3: year = String.valueOf((int) (Math.random()*10 + 1968));
                break;


                default: year = String.valueOf((int) (Math.random()*30 + 1968));
                break;

        }


        if(Integer.parseInt(mount)<10){
            mount = "0" + mount;
        }
        if(Integer.parseInt(day)<10){
            day = "0" + day;
        }

        Calendar c = Calendar.getInstance();
        int age = (int)((c.get(Calendar.YEAR)*12+c.get(Calendar.MONTH))-(Integer.parseInt(mount)+12*Integer.parseInt(year)))/12;
        return day + "." + mount + "." + year + ", " + age;
    }



    /**
     * Метод для генерации пароля
     * Пароль из 10 симвлов состоит из случайных букв латинского алфавита (строчные и прописные) и цифр от 0 до 9
     * @return Возвращает пароль
     */

    public String Password()
    {

        String Password="";

        String letters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        char[] symbols = new char[2*letters.length() + numbers.length()];
        int i = 0;
        for (char letter : letters.toCharArray()) {
            symbols[i] = letter;
            i++;
        }
        for (char letter : letters.toUpperCase().toCharArray()) {
            symbols[i] = letter;
            i++;
        }
        for (char number : numbers.toCharArray()) {
            symbols[i] = number;
            i++;
        }

        for(int j=0; j<10; j++){

            Password += symbols[(int)(Math.random()*symbols.length)];

        }

        return Password;

    }


    /**
     * Метод для генерации адреса электронной почты
     * @param LastName - фамилия пользователя
     * @param FirstName - имя пользователя
     * @param Date - дата рождения
     * Формат адреса - Первая буква имени + точка + фамилия (латиница) + день рождения + "@example.com"
     * @return Возвращает адрес почты
     */

    public String Email(String LastName, String FirstName, String Date)
    {

        LastName = LastName.toLowerCase();


        StringBuffer DateBuffer = new StringBuffer(Date);
        DateBuffer.delete(2, Date.length());

        return FirstName.toLowerCase().charAt(0)+"."+ Zamena(LastName)+DateBuffer +"@example.com";
    }


    /**
     * Метод для генерации логина
     * @param LastName - фамилия пользователя
     * @param FirstName - имя пользователя
     * Формат логина - имя (латиница) + _ + фамилия (латиница)
     * @return Возвращает логин
     */


    public String Login(String LastName, String FirstName)
    {

        LastName = LastName.toLowerCase();
        FirstName = FirstName.toLowerCase();



        return Zamena(FirstName)+"_"+Zamena(LastName);
    }






    /**
     * Генерация номера карты
     * Первые 15 цифр случайные, а последняя подбирается так, чтобы работал алгоритм ЛУНА
     * @return Возвращает номер карты (ставится пробел после каждой 4 цифры)
     */


    public String Paymants(){

        digits = new int[16];

        String card1 = String.valueOf((long) (Math.random() * 899 + 100));
        String card2 = String.valueOf((long) (Math.random() * 8999+1000));
        String card3 = String.valueOf((long) (Math.random() * 8999+1000));
        String card4 = String.valueOf((long) (Math.random() * 899+100));
        card = "";
        char[] charArray = (card1 + card2 + card3 + card4).toCharArray();

        digits[0] = 4;


        for (int i = 1; i < 14; i++) {
            digits[i] = Integer.parseInt(String.valueOf(charArray[i-1]));

        }


        int sum = 0;
        int digit;
        for(int i = 0; i<15; i++) {
            digit = digits[14-i];
            if ((i+1)%2 != 0) {
                digit *=2;
                if (digit > 9) {
                    digit -= 9;

                }
            }
            sum += digit;
        }

        sum = 10 - (sum%10);
        digits[15] = sum == 10 ? 0 : sum;


        for(int k=0; k<15; k++) {
            if((k+1)%4==0){
                card = card+String.valueOf(digits[k])+" ";
            }else{
                card = card+String.valueOf(digits[k]);
            }

        }
        return card + String.valueOf(digits[15]);
    }


    /**
     * Генерация cvc-кода карты -  3 случайные цифры
     * @return Возвращает сvc-код
     */

    public String PaymantsCVC()
    {
        String CVC = String.valueOf((int)(Math.random()*899+100));
        return CVC;
    }

    /**
     * Генерация срока действия карты
     * @return Вовзвращает срок действия карты
     */

    public String PaymantsDate()
    {
        String CVCMonth = String.valueOf((int)(Math.random()*12+1));
        String CVCYear = String.valueOf((int)(Math.random()*3+2019));
        return CVCMonth + "/"+ CVCYear;
    }


    /**
     * Этот метод заменяет в слове буквы не из латинского алфавита на соотвествующие (для генереации логина и адреса почты)
     * @param Name - имя/фамилия, в котором нужно заменить буквы
     * @return Вовзвращает имя/фамилию после изменения букв
     */


    public String Zamena(String Name){

        if(Name.contains("ö")){
            int positionSymbol = Name.indexOf("ö");
            StringBuilder NameBuilder = new StringBuilder(Name);
            NameBuilder.setCharAt(positionSymbol, 'o');
            Name = String.valueOf(NameBuilder);

        }

        if(Name.contains("ä")){
            int positionSymbol = Name.indexOf("ä");
            StringBuilder NameBuilder = new StringBuilder(Name);
            NameBuilder.setCharAt(positionSymbol, 'a');
            Name = String.valueOf(NameBuilder);

        }

        if(Name.contains("ü")){
            int positionSymbol = Name.indexOf("ü");
            StringBuilder NameBuilder = new StringBuilder(Name);
            NameBuilder.setCharAt(positionSymbol, 'u');
            Name = String.valueOf(NameBuilder);

        }

        if(Name.contains("ß")){
            int positionSymbol = Name.indexOf("ß");
            StringBuilder NameBuilder = new StringBuilder(Name);
            NameBuilder.setCharAt(positionSymbol, 'b');
            Name = String.valueOf(NameBuilder);

        }


        if(Name.contains("é")){
            int positionSymbol = Name.indexOf("é");
            StringBuilder NameBuilder = new StringBuilder(Name);
            NameBuilder.setCharAt(positionSymbol, 'e');
            Name = String.valueOf(NameBuilder);

        }


        return Name;

    }


}