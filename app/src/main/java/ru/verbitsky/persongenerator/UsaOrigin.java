package ru.verbitsky.persongenerator;


import com.google.android.gms.common.util.ArrayUtils;

import java.util.Arrays;

/**
 * Класс, который генерирует Имя, Фамилию и Мидл-Нэйм для фейковых пользователей с американским происхождением
 */

public class UsaOrigin implements Origin {

    private String sex;

    private String[] LastName = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller",
            "Wilson", "Moore", "Anderson", "Jackson", "White", "Thompson", "Garcia",
            "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall",
            "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill",
            "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner",
            "Phillips", "Campbell", "Parker",
            "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed",
            "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper",
            "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", " Gray",
            "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Bennett", "Wood",
            "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell",
            "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster",
            "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes",
            "Myers", "Ford", "Hamilton", "Graham", "Sullivan", "Wallace", " Woods"};


    private String[] MaleName = {"James", "John", "Robert", "Michael", "William", "David",
            "Richard", "Joseph", "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony",
            "Donald", "Mark", "Paul", "Steven", "Andrew", "Kenneth", "George", "Joshua",
            "Kevin", "Brian", "Edward", "Ronald", "Timothy", "Jason", "Jeffrey",
            "Ryan", "Gary", "Jacob", "Nicholas", "Eric", "Stephen","Jonathan", "Larry", "Justin", "Scott",
            "Frank", "Brandon", " Raymond",
            "Gregory", "Benjamin", "Samuel", "Patrick", "Jack", "Dennis", "Jerry", "Tyler"};

    private String[] FemaleName = {"Mary", "Patricia", "Jennifer", "Elizabeth", "Linda", "Barbara", "Susan", "Jessica",
            "Margaret", "Sarah", "Karen", "Nancy", "Betty", "Lisa", "Dorothy", "Sandra", "Ashley", "Kimberly",
            "Donna", "Carol", "Michelle", "Emily", "Amanda", "Helen", "Melissa", "Deborah", "Stephanie", "Laura",
            "Rebecca", "Sharon", "Cynthia", "Kathleen", "Amy", "Shirley", "Anna",
            "Angela", "Ruth", "Brenda", "Pamela", "Nicole", "Katherine", "Virginia",
            "Catherine", "Christine", "Samantha", "Debra", "Janet", "Rachel", "Carolyn","Emma"};


    private String[] MiddleName = {"A.", "B.", "C.", "D.", "E.", "F.",
            "G.", "I.", "H.", "J.", "K.", "L.", "M.", "N.",
            "P.", "R.", "S.", "T.", "V.", "Z.", "Y."};


    public UsaOrigin(String sex){

        this.sex = sex;

    }



    /**
     * @return Возвращает случайную фамилию из массива данных фамилий (список самых популярных американских фамилий)
     */


    @Override
    public String lastName() {

        return LastName[(int)(Math.random()*LastName.length)];
    }






    /**
     * Аналогично как в
     * @see RussianOrigin#name()
     * @return Возвращает случайное имя из массива данных имен (список самых популярных мужских американских имен)
     */



    @Override
    public String name() {


        if(sex.equals("male")){

            return MaleName[(int)(Math.random()*MaleName.length)];

        }else{

            return FemaleName[(int)(Math.random()*FemaleName.length)];

        }


    }


    /**
     * @return Возвращает мидл-нэйм (случайная латинская буква)
     */

    @Override
    public String middleName() {

        return MiddleName[(int)(Math.random()*MiddleName.length)];
    }




    @Override
    public String[] allWords() {

        return ArrayUtils.concat(LastName, MaleName, FemaleName, MiddleName);
    }


    @Override
    public String getLocale(String s) {

        if(Arrays.asList(LastName).contains(s)){

            return this.getClass().getName()+ " - LastName";

        }else{

            if(Arrays.asList(MaleName).contains(s)) {

                return this.getClass().getName()+ " - MaleName";
            }else{

                return this.getClass().getName()+" - FemaleName";

            }
        }



    }


}
