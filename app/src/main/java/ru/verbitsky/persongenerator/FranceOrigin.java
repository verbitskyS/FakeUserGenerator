package ru.verbitsky.persongenerator;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

/**
 * Класс, который генерирует Имя и Фамилию для фейковых пользователей с французским происхождением
 */

public class FranceOrigin implements Origin {

    private String sex;



    private String[] LastName = {"Bernard", "Durand", "Leroy", "Moreau", "Laurent", "Lefebvre", "Bertrand",
            "Roux", "Vincent", "Fournier", "Morel", "Girard", "Andre", "Martin",
            "Lefevre", "Mercier", "Dupont", "Lambert", "Bonnet", "Francois", "Martinez", "Legrand",
            "Garnier", "Faure", "Rousseau", "Blanc", "Guerin", "Muller", "Roussel",
            "Perrin", "Morin", "Mathieu", "Clement", "Gauthier", "Dumont", "Lopez", "Fontaine", "Chevalier",
            "Robin", "Masson", "Sanchez", "Gerard",
            "Boyer", "Lemaire", "Duval", "Gautier", "Roche", "Meyer", "Meunier", "Perez",
            "Marchand", "Dufour", "Blanchard", "Barbier", "Brun", "Dumas", "Brunet",
            "Schmitt", "Leroux", "Colin", "Fernandez", "Pierre", "Renard", "Arnaud",
            "Rolland", "Caron", "Aubert", "Giraud", "Leclerc", "Vidal", "Bourgeois", "Renaud",
            "Lemoine", "Picard", "Gaillard", "Philippe", "Leclercq", "Lacroix", "Fabre",
            "Rodriguez", "Olivier", "Hubert", "Riviere", "Guillaume", "Gonzalez", "Lecomte",
            "Menard", "Fleury", "Deschamps", "Carpentier", "Julien", "Benoit", "Maillard",
            "Marchal", "Aubry", "Vasseur"};

    private String[] MaleName = {"Nathan", "Lucas", "Enzo", "Louis", "Hugo", "Gabriel",
            "Ethan", "Mathis", "Jules", "Arthur", "Tom", "Noah", "Maxime", "Yanis",
            "Adam", "Thomas", "Léo", "Paul", "Evan", "Nolan", "Axel", "Antoine",
            "Timeo", "Mael", "Raphael", "Alexandre", "Théo", "Sacha", "Noa",
            "Baptiste", "Maxence", "Clément", "Mathéo", "Gabin", "Alexis","Rayan", "Quentin", "Valentin", "Mathys",
            "Victor", "Samuel", " Kylian",
            "Romain", "Esteban", "Simon", "Aaron", "Lorenzo", "Lenny", "Robin", "Benjamin"};

    private String[] FemaleName = {"Emma", "Manon", "Lola", "Jade", "Camille", "Sarah", "Louise", "Lilou",
            "Léa", "Clara", "Chloé", "Eva", "Lina", "Inès", "Louna", "Romane", "Maelys", "Juliette",
            "Lucie", "Zoé", "Ambre", "Alice", "Lou", "Léna", "Lisa", "Jeanne", "Louane", "Ines",
            "Mathilde", "Charlotte", "Marie", "Lea", "Chloe", "Anna", "Nina",
            "Pauline", "Lana", "Laura", "Lily", "Alicia", "Julie", "Julia",
            "Rose", "Margaux", "Noemie", "Elise", "Margot", "Zoe", "Elsa","Clémence"};



    public FranceOrigin(String sex){

        this.sex = sex;

    }



    /**
     * @return Возвращает случайную фамилию из массива данных фамилий (список самых популярных французских фамилий)
     */


    @Override
    public String lastName() {


        return LastName[(int)(Math.random()*LastName.length)];
    }




    /**
     * Аналогично как в
     * @see RussianOrigin#name()
     * @return Возвращает случайное имя из массива данных имен (список самых популярных французских имен)
     */



    @Override
    public String name() {


        if(sex.equals("male")) {
            return MaleName[(int)(Math.random()*MaleName.length)];

        }else{
            return FemaleName[(int) (Math.random() * FemaleName.length)];
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
