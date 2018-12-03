package ru.verbitsky.persongenerator;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

/**
 * Класс, который генерирует Имя и Фамилию для фейковых пользователей с немецким происхождением
 */

public class GermanyOrigin implements Origin{



   private String[] LastName = {"Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Mayer", "Wagner",
            "Becker", "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", " Richter",
            "Klein", "Wolf", "Schröder", "Neumann", "Schwarz", "Zimmermann", "Braun", "Krüger",
            "Hartmann", "Lange", "Schmitt", "Werner", "Schmitz", "Krause", "Meier",
            "Lehmann", "Schmid", "Schulze", "Köhler", "Herrmann", "König", "Walter", "Mayer", "Huber",
            "Kaiser", "Fuchs", "Peters", "Lang",
            "Scholz", "Möller", "Weiß", "Jung", "Hahn", "Schubert", "Vogel", "Friedrich",
            "Keller", "Günther", "Frank", "Berger", "Winkler", "Roth", "Beck",
            "Lorenz", "Baumann", "Franke", "Albrecht", "Schuster", "Simon", "Ludwig",
            "Böhm", "Winter", "Kraus", "Martin", "Schumacher", "Krämer", "Vogt", "Stein",
            "Jäger", "Otto", "Sommer", "Seidel", "Heinrich", "Brandt",
            "Haas", "Schreiber", "Graf", "Schulte", "Dietrich", "Ziegler", "Kühn",
            "Pohl", "Engel", "Horn", "Busch", "Bergmann", "Thomas", "Voigt",
            "Sauer", "Arnold", "Pfeiffer"};




    private String[] FemaleName = {"Mia", "Emma", "Sofia", "Hannah", "Emilia", "Anna", "Marie", "Mila",
            "Lina", "Leah", "Lena", "Leonie", "Amelie", "Luisa", "Johanna", "Emilie", "Clara", "Sophie",
            "Charlotte", "Lilly", "Lara", "Laura", "Leni", "Nele", "Ella", "Maja", "Mathilda", "Frieda",
            "Lia", "Greta", "Sarah", "Lotta", "Pia", "Julia", "Melina",
            "Paula", "Alina", "Marlene", "Elisa", "Lisa", "Mira", "Victoria",
            "Annie", "Nora", "Mara", "Isabelle", "Helena", "Maria", "Josephine","Jana"};


    private String[] MaleName = {"Ben", "Paul", "Jonas", "Elias", "Leon", "Finn",
            "Noah", "Louis", "Lucas", "Felix", "Luca", "Maximilian", "Henry", "Max",
            "Oskar", "Emil", "Liam", "Jacob", "Moritz", "Anton", "Julian", "Theo",
            "Niklas", "David", "Philipp", "Alexander", "Tim", "Matteo", "Milan",
            "Leo", "Tom", "Mads", "Karl", "Erik", "Linus","Jonathan", "Jan", "Fabian", "Leonard",
            "Samuel", "Rafael", " Jonah",
            "Jannik", "Simon", "Vincent", "Mika", "Hannes", "Lennard", "Benjamin", "Aaron"};


    String sex;

    public GermanyOrigin(String sex){

        this.sex = sex;

    }



    /**
     * @return Возвращает случайную фамилию из массива данных фамилий (список самых популярных немецких фамилий)
     */


    @Override
    public String lastName() {

        return LastName[(int)(Math.random()*LastName.length)];
    }





    /**
     * Аналогично как в
     * @see RussianOrigin#name()
     * @return Возвращает случайное имя из массива данных имен (список самых популярных немецких имен)
     */


    @Override
    public String name() {



        if(sex.equals("male")) {

            return MaleName[(int) (Math.random() * MaleName.length)];
        }else{

            return FemaleName[(int)(Math.random()*FemaleName.length)];

        }
    }



    @Override
    public String middleName() {
        return "";
    }


    /**
     * @return Возвращает массив, который является конкатенацией всех массивов String использованных в классе (для тестов)
     */

    @Override
    public String[] allWords() {



        return ArrayUtils.concat(LastName, MaleName, FemaleName);
    }



    @Override
    public String getLocale(String s) {

        if(Arrays.asList(LastName).contains(s)){

            return this.getClass().getName()+ " - LastName";

        }else{

            if(Arrays.asList(MaleName).contains(s)) {

                return this.getClass().getName()+ " - MaleName";
            }else{

                return this.getClass().getName()+ " - FemaleName";

            }
        }



    }
}