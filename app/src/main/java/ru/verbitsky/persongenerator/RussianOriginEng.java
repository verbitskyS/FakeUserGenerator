package ru.verbitsky.persongenerator;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

public class RussianOriginEng implements Origin {


    private String sex;


    private String[] FemaleLastNameEng = {"Ivanova", "Smirnova", "Kuznetsova", "Sokolova", "Popova", "Lebedeva", "Kozlova",
            "Novikova", "Morozova", "Petrova", "Volkova", "Solovyova", "Vasilieva", " Zaitseva",
            "Pavlova", "Semyonova", "Golubeva", "Vinogradova", "Bogdanova", "Vorobieva", "Fedorova", "Mikhailova",
            "Belyaeva", "Tarasova", "Belova", "Komarova", "Orlova", "Kiseleva", "Makarova",
            "Andreeva", "Kovaleva", "Ilyina", "Guseva", "Titova", "Kuzmina", "Kudryavtseva", "Baranova", "Kulikova", "Alekseeva",
            "Stepanova", "Yakovleva", " Sorokina",
            "Sergeeva", "Romanova", "Zakharova", "Borisova", "Koroleva", "Gerasimova", "Ponamareva", " Grigorieva",
            "Lazareva", "Medvedeva", "Ershova", "Nikitina", "Soboleva", "Ryabova", "Polyakova",
            "Tsvetkova", "Danilova", "Zhukova", "Frolova", "Zhuravleva", "Nikolaeva", " Krylova",
            "Maksimova", "Sidorova", "Osipova", "Belousova", "Fedotova", "Dorofeeva", "Egorova", " Matveeva",
            "Bobrova", "Dmitrieva", "Kalinina", "Anisimova", "Petukhova", "Antonova", "Timofeeva",
            "Nikiforova", "Veselova", "Filippova", "Markova", "Bolshakova", "Sukhanova", "Mironova",
            "Shiryaeva", "Alexandrova", "Konovalova", "Shestakova", "Kazakova", "Efimova", " Denisova",
            "Gromova", "Fomina", "Davydova", "Melnikova", "Shcherbakova", "Blinova", " Kolesnikova"};


    private String[] MaleLastNameEng = {"Ivanov", "Smirnov", "Kuznetsov", "Sokolov", "Popov", "Lebedev",
            "Kozlov", "Novikov", "Morozov", "Petrov", "Volkov", "Soloviev", "Vasilyev", "Zaitsev",
            "Pavlov", "Semenov", "Blue", "Vinogradov", "Bogdanov", "Fedorov", "Mikhailov",
            "Belyaev", "Tarasov", "Belov", "Komarov", "Orlov", "Kiselev", "Makarov",
            "Andreev", "Kovalev", "Illyin", "Gusev", "Titov", "Kuzmin", "Kudryavtsev", "Baranov", "Kulikov", "Alekseev",
            "Stepanov", "Yakovlev", "Sorokin",
            "Sergeev", "Romanov", "Zakharov", "Borisov", "Korolev", "Gerasimov", "Ponamarev", "Grigoriev",
            "Lazarev", "Medvedev", "Ershov", "Nikitin", "Sobolev", "Ryabov", "Polyakov",
            "Tsvetkov", "Danilov", "Zhukov", "Frolov", "Zhuravlev", "Nikolaev", "Krilov",
            "Maximov", "Sidorov", "Osipov", "Belousov", "Fedotov", "Dorofeev", "Egorov", "Matveev",
            "Pavlov", "Semenov", "Golubev", "Vinogradov", "Bogdanov", "Vorobiev", "Fedorov", "Mikhailov",
            "Bobrov", "Dmitriev", "Kalinin", "Anisimov", "Petuhov", "Antonov", " Timofeev",
            "Nikiforov", "Veselov", "Filippov", "Markov", "Bolshakov", "Sukhanov", "Mironov",
            "Shiryaev", "Alexandrov", "Konovalov", "Shestakov", "Kazakov", "Efimov", "Denisov",
            "Gromov", "Fomin", "Davydov", "Melnikov", "Shcherbakov", "Blinov", " Kolesnikov"};


    private String[] MaleNameEng = {" Alexander", "Alexey", "Andrey", "Anton", "Artem", "Vasily",
            "Vadim", "Michael", "Jury", "Dmitry", "Nikolay", "Gleb", "Sergey", "Oleg",
            "Pavel", "Semyon", "Petr", "Stanislav", "Fedor", "Stepan", "Victor", "Ilya",
            "Ivan", "Konstantin", "Igor", "Daniil", "Georgiy", "Roman", "Vladislav",
            "Nikita", "Evgeniy", "Kirill", "Denis", "Vyacheslav", "Anatoly"};


    private String[] FemaleNameEng = {"Anastasia", "Sofia", "Maria", "Veronica", "Polina", "Victoria", "Darya", "Xenia",
            "Lubov", "Ekaterina", "Valeria", "Alena", "Olga", "Alina", "Kristina", "Anna", "Elizaveta", "Julia",
            "Elena", "Irina", "Yana", "Alexandra", "Tatiana", "Natalia", "Marina", "Svetlana", "Olesya", "Eugenia",
            "Lyudmila", "Larisa", "Arina", "Vera", "Galina", "Nadezhda", "Tamara",};

    private String[] FemaleFatherNameEng = {"Alexandrovna", "Alekseevna", "Andreevna", "Antonovna", "Artemovna", "Vasilievna",
            "Vadimovna", "Mikhailovna", "Yurievna", "Dmitriyevna", "Nikolayevna", "Glebovna", "Sergeevna", "Olegovna",
            "Pavlovna", "Semyonovna", "Petrovna", "Stanislavovna", "Fedorovna", "Stepanovna", "Viktorovna", "Ilyevna",
            "Ivanovna", "Konstantinovna", "Igorevna", "Daniilovna", "Georgievna", "Romanovna", "Vladislavovna",
            "Nikitichna", "Evgenievna", "Kirillovna", "Denisovna", "Vyacheslavovna", "Anatolievna", "Ruslanovna"};




    private String[] MaleFatherNameEng =
            {"Alexandrovich", "Alekseevich", "Andreevich", "Antonovich", "Artemovich", "Vasilievich",
                    "Vadimovich", "Mikhailovich", "Yurievich", "Dmitriyevich", "Nikolayevich", "Glebovich", "Sergeevich", "Olegovich",
                    "Pavlovich", "Semenovich", "Petrovich", "Stanislavovich", "Fedorovich", "Stepanovich", "Viktorovich", "Ilyich",
                    "Ivanovich", "Konstantinovich", "Igorevich", "Daniilovich", "Georgievich", "Romanovich", "Vladislavovich",
                    "Nikitich", "Evgenievich", "Kirillovich", "Denisovich", "Vyacheslavovich", "Anatolyevich"};



    public RussianOriginEng(String sex){

        this.sex = sex;

    }


    /**
     * Генерирует имя на английском и присваивает номер сгенированного имя статической переменной nameInt из класса MainActivity
     * (для того чтобы имя на русском соответствовало имени на английском)
     * @see RussianOrigin#name()
     * @return Возвращает nameInt-тое женское или мужское имя в зависимости от переменной sex из массива данных имен (список самых популярных русских имен)
     */

    @Override
    public String name() {

        if(sex.equals("male")){

            MainActivity.nameInt =(int)(Math.random()*MaleNameEng.length);

            return MaleNameEng[MainActivity.nameInt];

        }else{

            MainActivity.nameInt =(int)(Math.random()*FemaleNameEng.length);

            return FemaleNameEng[MainActivity.nameInt];

        }

    }



    /**
     * Аналогично, что и в методе для генерации имени на английском
     * @see RussianOrigin#lastName()
     * @return Возвращает lastNameInt-тое фамилию из массива данных фамилий (список самых популярных русских фамилий)
     */


    @Override
    public String lastName() {

        if(sex.equals("male")) {
            MainActivity.lastNameInt = (int) (Math.random() * MaleLastNameEng.length);
            return MaleLastNameEng[MainActivity.lastNameInt];
        }else {
            MainActivity.lastNameInt = (int) (Math.random() * FemaleLastNameEng.length);
            return FemaleLastNameEng[MainActivity.lastNameInt];
        }


    }





    /**
     * Аналогично, только отчество на английском
     * @see RussianOrigin#middleName()
     * @return Возвращает fatherNameInt-тое отчество из массива данных отчеств
     */



    @Override
    public String middleName() {

        if(sex.equals("male")){
            MainActivity.fatherNameInt =(int)(Math.random()*MaleFatherNameEng.length);
            return MaleFatherNameEng[MainActivity.fatherNameInt];
        }else{
            MainActivity.fatherNameInt =(int)(Math.random()*FemaleFatherNameEng.length);
            return FemaleFatherNameEng[MainActivity.fatherNameInt];
        }


    }


    @Override
    public String[] allWords() {

        return ArrayUtils.concat(MaleLastNameEng,FemaleLastNameEng, MaleNameEng,
                FemaleNameEng, MaleFatherNameEng, FemaleFatherNameEng);
    }




    @Override
    public String getLocale(String s) {

        if(Arrays.asList(FemaleLastNameEng).contains(s)){

            return  this.getClass().getName()+" - FemaleLastNameEng";

        }else{

            if(Arrays.asList(MaleLastNameEng).contains(s)) {

                return  this.getClass().getName()+" - MaleLastNameEng";
            }else{

                if(Arrays.asList(MaleNameEng).contains(s)) {

                    return  this.getClass().getName()+" - MaleNameEng";
                }else{

                    if(Arrays.asList(FemaleNameEng).contains(s)){

                        return  this.getClass().getName()+" - FemaleNameEng";

                    }else{

                        if(Arrays.asList(MaleFatherNameEng).contains(s)){

                            return  this.getClass().getName()+" - MaleFatherNameEng";

                        }else{

                            return  this.getClass().getName()+" - FemaleFatherNameEng";
                        }

                    }

                }

            }
        }

    }

}
