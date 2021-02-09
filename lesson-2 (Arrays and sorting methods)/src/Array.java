//Создание интерфейса коллекции для последующих реализаций классов динамической коллекции и класса сортировки
/*TODO Важно!!! Интерфейс объявлен с помощью обобщения(Generic) E,
       что позволяет явным образом не указывать какой тип данных будет приниматься
       массивом на вход
 */
public interface Array<E> {
    //Метод добавления элемента в коллекцию(на вход принимает обобщенное значение элемента E)
    void add(E value);

    //Метод получения элемента коллекции(на вход принимает индекс - index; возвращает значение элемента E)
    E get(int index);

    /*
    Метод удаления элемента массива по значению(на вход принимает значение элемента E;
    возвращает true - при успешном удалении, false - при отсутсвии принятого значения E в массиве)                  */
    boolean remove(E value);

    //Метод удаления элемента массива по индексу(на вход принимает индекс - index; возвращает удаленное значение E)
    E remove(int index);

    //Метод, возвращающий значение индекса(позицию) типа int, которая определяет положение элемента value в массиве data
    int indexOf(E value);

    /*
    Метод поиска элемента в коллекции (на вход принимает элемент E; возвращает true, если такой элемент есть в массиве,
    в противном случае возвращает false)                                                                            */
    boolean contains(E value);

    /*
    Метод проверки коллекции - пустая или нет (возвращает значение true, если коллекция не содержит элементов;
    false - если в коллекции 1 элемент или более)                                                                   */
    boolean isEmpty();

    //Метод, возвращающий текущий размер коллекции (возвращает значение int)
    int size();

    //Метод, который выводит в консоль текущее состояние коллекции
    void display();

    void fillingArrayOfRandom();

    void copyFullArray(E[] origArr, E[] targetArr);

    long sortBubble();
    long sortSelect();
    long sortInsert();

    //hw
    E[] toArray();

    Array<E> copy();

}
