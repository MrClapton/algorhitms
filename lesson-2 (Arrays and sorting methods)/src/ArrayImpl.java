import java.util.Arrays;
import java.util.Random;

/**
 * Объявление класса ArrayImpl, реализующего интерфейс Array,
 * в котором описан функционал динамической коллекции(реализации методов)
 */
//public class ArrayImpl<E> implements Array<E> {
public class ArrayImpl<E extends Comparable<? super E>> implements Array<E> {


//TODO ################Поля класса: #############################################
    /**
     * Константа, в которой задается емкость массива по умолчанию
     * (для случая, когда используется дефолтный конструктор без параметра)
     */
    protected static final int DEFAULT_CAPACITY = 10000;
    /**
     * Переменная data типа E(обобщенная) - одномерный массив
     */
    protected E[] data;
    /**
     * Переменная size типа int, которая отвечает за текущий размер массива
     */
    protected int size;

    protected static Random random;


//TODO ###############Конструкторы класса: ######################################

    /**
     * Конструктор класса ArrayImpl, не принимающий входные данные
     */
    public ArrayImpl() {
        /**
         Конструктор не имеет входных данных, поэтому для инициализации использует значение
         константы DEFAULT_CAPACITY.
         Т.к. мы имеем 2 точки инициализации в классе ArrayImpl в лице 2-х конструкторов,
         тогда из конструктора, который принимает меньше параметров, вызовем при помощи
         ключевого слова this второй конструктор, передав в него константу DEFAULT_CAPACITY
         ↓                                                                                           */
        this(DEFAULT_CAPACITY);
    }

    /**
     * Конструктор класса ArrayImpl,
     * принимающий на вход initialCapacity - исходный размер для инициализации.
     * -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
     * _____________________________________________________________________________
     * SuppressWarnings("unchecked") - заглушка предупреждения(warning) о том,что каст
     * Comparable[initialCapacity] к типу E[] может быть с ошибкой
     */
    @SuppressWarnings("unchecked")
    public ArrayImpl(int initialCapacity) {
        /**
         Инициализация массива данных data.
         ↓                                                           */
        this.data = (E[]) new Comparable[initialCapacity];
        //this.data = (E[]) new Object[initialCapacity];
        /**
         ↑      this.data = new E[initialCapacity] - WRONG!!!
         Здесь должна была быть инициализация данных обобщенного массива.
         Но поскольку в Java нельзя инициализировать обобщенный тип данных без явного указания,
         тогда необходимо описать инициализацию так --->> this.data = (E[]) new Object[initialCapacity]; */
    }


//TODO ########################Реализации методов: ##################################


    // Все что касается метода add:

    /**
     * -_-_-_-_-_-_-_-_-_-_-_-__Метод добавления элемента-_-_-_-_-_-_-_-_-_-_-_-_-_
     * На вход метод принимает значение элемента для добавления - value._______
     * Интересное замечание - значение size всегда будет на 1 больше
     * индекса последнего элемента, поэтому с помощью size
     * и производится добавление нового элемента в массив --> data[size++] = value;
     */
    @Override
    public void add(E value) {       //O(1) -> O(n)
        checkAndGrow();              //Вызов проверки размерности массива
        /**
         Постфиксная инкрементация позволяет сначала добавить элемент в индекс size,
         а только после этого увеличить его на 1
         ↓                                                             */
        data[size++] = value;
    }

    /**
     * -_-_-_-_-_-_-_-_-_-_-_-_-_Метод проверки массива-_-_-_-_-_-_-_-_-_-_-_-_-_-_
     * В случае добавления элемента, когда массив переполнен,
     * в переменную data будет передано значение вызванного метода copyOf класса Arrays,
     * который увеличит размерность массива до значения calculateNewLength()
     */
    protected void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, calculateNewLength());
        }
    }

    /**
     * -_-_-_-_-_-_-_-Метод расчета размера для увеличенного массива-_-__-_-_-_-_-_
     * В случае, если на вход он получит значение 0, тогда
     * итоговый size будет равен 1, иначе значение size будет увеличено в 2 раза
     *
     * @return int
     */
    private int calculateNewLength() {
        return size == 0 ? 1 : size * 2;
    }


    // Все что касается метода get:
    /**
     * -_-_-Метод получения значения массива по индексу-_-_-
     *
     * @return E
     */
    @Override
    public E get(int index) {       // O(1)
        checkIndex(index);          //Проверка индекса из входных данных
        return data[index];
    }

    /**
     * -_-_-_-_-_-_-_-_-_-_-Метод проверки полученного индекса-_-_-_-_-_-_-_-_-_-_-
     * Метод проверяет значение индекса, полученного на вход, на валидность
     * (сравнивая значение с 0 и size - размером массива).
     * В случае ошибочных данных бросает IndexOutOfBoundsException
     *
     * @throws IndexOutOfBoundsException
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            String errMsg = String.format("Incorrect 'index' value: %d; max value is %d", index, size - 1);
            throw new IndexOutOfBoundsException(errMsg);
        }
    }


    // Все что касается методов remove:

    /**
     * _-_-_-Метод, который реализует удаление элемента массива по индексу_-_-_-
     * Этапы:
     * 1) сначала производится проверка входных данных --> checkIndex(index);
     * 2) в переменную removedValue записывается элемент для удаления;
     * 3) исходный массив копируется в себя же (System.arraycopy) со смещением всех элементов,
     * которые находятся правее удаленного, на 1 позицию левее;
     * 4) в завершение метода, производится обнуление ссылки на последний элемент массива и
     * возвращение значения removedValue
     *
     * @return E
     */
    @Override
    public E remove(int index) {        // O(n)
        checkIndex(index);              //Проверка значения index

        E removedValue = data[index];
        if ((size - index - 1) >= 0) {  //Проверка количества оставшихся после удаления элементов для ограничения удаления
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
    /*
        В идеале здесь нужно использовать метод trimToSize,
        который как раз приведет массив из size элементов к виду (size-1)
        после удаления одного элемента!                                                                                                                   */
        data[--size] = null; // clear to let GC do its work || явное обнуление ссылки на оставшийся элемент массива, чтобы сборщик мусора смог прибраться.
        return removedValue;
    }

    /**
     * _-_-_-Метод, который реализует удаление элемента value из массива data_-_-_-
     *
     * @return boolean
     */
    @Override
    public boolean remove(E value) {
        return remove(indexOf(value)) != null;
    }


    // Вспомогательные методы:

    /**
     * _-_-_-Метод, проверки содержания value в массиве data-_-_-_
     *
     * @return boolean
     */
    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    /**
     * _-_-_-_-_-_-Метод, который возвращает индекс элемента массива-_-_-_-_-_-_
     * ____*Прим.: в случае, если по значению value в массиве не найден элемент,
     * тогда метод возвращает значение -1.
     *
     * @return int
     */
    @Override
    public int indexOf(E value) {       // O(n)
        for (int i = 0; i < size; i++) {
            if (value.equals(data[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * _-_-_-Метод проверки пустой ли массив data-_-_-_
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * _-_-_-_-_-_Метод, который возвращает текущую размерность массива_-_-_-_-_-_
     *
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * _-_-_-_-Метод, который выводит в консоль текущее состояние коллекции_-_-_-_-_
     */
    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public void fillingArrayOfRandom() {
        //Random random = new Random();
        for (int i=0; i<size; i++) {
            data[i] = (E) Integer.valueOf(random.nextInt(1000));
        }
    }

    @Override
    public void copyFullArray(E[] origArr, E[] targetArr) {
        System.arraycopy(origArr, 0, targetArr, 0, size);
    }

/*
    public void copyFullArray(E[] origArr, E[] targetArr) {
        System.arraycopy(origArr, 0, targetArr, 0, size);
    }
*/


    // Методы сортировок:

    /**
     * _-_-_-_-Метод, который реализует сортировку массива "пузырьком"_-_-_-_-_
     * Самый слабый по эффективности метод: O(n^2) - сравнения, O(n^2) - перестановки.
     */
    @Override
    public long sortBubble() {      // O(n^2) - compare   O(n^2) - exchange
        long start = System.currentTimeMillis();

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
        long finish = System.currentTimeMillis();
        return (finish - start);
    }

    /**
     * _-Метод, который реализует обмен значениями между двумя элементами массива-_
     * Реализован с использованием буфера 'temp', в котором хранится значение
     * одного из элементов во время замены данных.
     */
    private void swap(int indexA, int indexB) {
        E temp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = temp;
    }


    /**
     * -_-_-_-Метод, который реализует сортировку массива методом "выбора"_-_-_-_-
     * Эффективнее "пузырька" по количеству перестановок - O(n), но такой же слабый по количеству сравнений - O(n^2)
     */
    @Override
    public long sortSelect() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(minIndex, i);
        }
        long finish = System.currentTimeMillis();
        return (finish - start);
    }

    // O(n^2) --> O(n) - compare
    // O(n) --> O(0) = exchange

    /**
     * -_-_-_-Метод, который реализует сортировку массива методом "вставки"_-_-_-_-
     * Самый эффективный в теории из трех методов сортировки, поскольку он зависит от изначального состояния массива
     * (чем больше исходный массив отсортирован, тем эффективнее применение метода "вставки") _______________________________
     * O(n^2) --> O(n) - сравнения, O(n) --> O(0) - перестановки
     */
    @Override
    public long sortInsert() {
        long start = System.currentTimeMillis();

        for (int i = 1; i < size; i++) {
            E temp = data[i];
            int in = i;
            while (in > 0 && data[in - 1].compareTo(temp) >= 0) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
        long finish = System.currentTimeMillis();
        return (finish - start);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        if (size > 0) {
            sb.append(data[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }


    //hw
    @Override
    public Array<E> copy() {
        ArrayImpl<E> array = new ArrayImpl<>(size);
        array.size = size;
        array.data = Arrays.copyOf(this.data, size);
        return array;
    }

    @Override
    public E[] toArray() {
        return data;
    }


}
