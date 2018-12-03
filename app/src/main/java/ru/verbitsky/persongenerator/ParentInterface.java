package ru.verbitsky.persongenerator;

public interface ParentInterface {

    /**
     * @return Возвращает массив из всех String, использованных в классе
     */
    String[] allWords();
    /**
     * @return Возвращает имя класса и имя ссылки на объект String[], в котором расположена строка smth
     */
    String getLocale(String smth);

}
