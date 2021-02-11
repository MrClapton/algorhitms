package homework.Task3;

import java.util.EmptyStackException;

public class DequeueImpl<Item extends Comparable> {
    private Item[] list;
    private final int DEFAULT_CAPACITY = 10;
    private int begin;
    private int end;
    private int startBeginEnd;

    public DequeueImpl(Item[] list) {
        //this.list = list;
        list = (Item[]) new Comparable[DEFAULT_CAPACITY];
        initDequeue();
    }

    DequeueImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong Size! --> " + capacity);
        }
        list = (Item[]) new Comparable[capacity];
        this.startBeginEnd = list.length / 2;
        initDequeue();
    }

    protected DequeueImpl(int capacity, int startBeginEnd) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong size! --> " + capacity);
        }
        list = (Item[]) new Comparable[capacity];
        this.startBeginEnd = startBeginEnd;
        initDequeue();
    }

    private void initDequeue(){
        begin = startBeginEnd;
        end = begin;
    }

    void insertRight(Item item) {
        if (isFull()) {
            //если массив заполнен полностью, увеличиваем его
            reCapacity(list.length + DEFAULT_CAPACITY);
        }
        //сдвигаем хвост очереди наружу, кроме случая если очередь пустая
        if(!isEmpty()){
            end = shiftEndOutward();
        }
        list[end] = item;
    }

    void insertLeft(Item item) {
        if (isFull()) {
            reCapacity(list.length + DEFAULT_CAPACITY);
        }
        //сдвигаем голову очереди наружу, кроме случая если очередь пустая
        if(!isEmpty()){
            begin = shiftBeginOutward();
        }
        list[begin] = item;
    }

    Item removeLeft() {
        //запоимнаем элемент в начале очереди
        Item value = peekLeft();
        //обнуляем ссылку на объект в ячейке элемента
        list[begin] = null;
        //сдвигаем начало внутрь
        begin = shiftBeginInward();
        isEmpty();
        return value;
    }

    Item removeRight() {
        Item value = peekRight();
        list[end] = null;
        end = shiftEndInward();
        isEmpty();
        return value;
    }

    Item peekLeft() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[begin];
    }

    protected Item peekRight() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[end];
    }

    private int nextRightIndex(int index) {
        return (index + 1) % list.length;
    }

    private int nextLeftIndex(int index) {
        return index != 0 ? index - 1 : list.length - 1;
    }

    private int shiftBeginOutward(){
        return nextLeftIndex(begin);
    }

    protected int shiftEndOutward(){
        return nextRightIndex(end);
    }

    private int shiftBeginInward(){
        return nextRightIndex(begin);
    }

    protected int shiftEndInward(){
        return nextLeftIndex(end);
    }


    protected int queueLength() {
        //если индексы начала и конца не равны
        if(end != begin && list[begin] != null && list[end] != null){
            //если есть, возвращаем количество элементов в очереди
            return isOrder() ? end - begin + 1: list.length - begin + end + 1;//e2 b5 = 8 (10 - 5 + 2 + 1)
        } else {//если равны
            //если они указывают на не нулевые элементы
            if (list[begin] != null && list[end] != null) {
                //значит в очереди один элемент
                return 1;
            } else {
                //если какой-то элемент нулевой, значит в очереди никого нет
                return 0;
            }
        }
    }


    //проверяем не пустой ли массив. Да - длина очереди равна нулю
    //Если пустой, уменьшаем его до дефолтной вместимости
    protected boolean isEmpty() {
        if(queueLength() == 0 && list.length > DEFAULT_CAPACITY){
            resetCapacity();
            return true;
        }
        return queueLength() == 0;
    }


    //уменьшаем массив до дефолтной вместимости
    private void resetCapacity() {
        Item[] tempArr = (Item[]) new Comparable[DEFAULT_CAPACITY];
        list = tempArr;
        //устанавливаем начальные значения начала и конца очереди
        initDequeue();
    }


    //проверяем полностью ли заполнен массив. да - длина очереди равна длине массива
    protected boolean isFull() {
        return queueLength() == list.length;
    }


    //определяем какой порядок расположения.
    // Прямой порядок(true) - если конец очереди справа от начала или их индексы равны
    private boolean isOrder(){
        return end >= begin;
    }


    //увеличивает массив при полном его заполнении(создает новую копию большего размера)
    protected void reCapacity(int newCapacity){
        Item[] tempArr = (Item[]) new Comparable[newCapacity];
        //рассчитаваем приращение размера массива
        int delta = newCapacity - list.length;
        //если порядок в очереди прямой или длина очереди 1
        if(isOrder()){
            System.arraycopy(list, begin, tempArr, begin + delta / 2, queueLength());
            //вычисляем новые значения начала и конца очереди
            begin += delta / 2;
            end += delta / 2;
        } else {//если порядок очереди обратный
            //копируем левый кусок
            System.arraycopy(list, 0, tempArr, 0, end + 1);
            //копируем правый кусок
            System.arraycopy(list, begin, tempArr, begin + delta, list.length - begin);
            //вычисляем новые значения начала очереди(индекс конца не меняется)
            begin += delta;
        }
        list = tempArr;
    }


    //очистить очередь
    public void eraseQueue(){
        resetCapacity();
    }

    public Item[] getList() {
        return list;
    }

    public void setList(Item[] list) {
        this.list = list;
    }

    //возвращает размер массива
    public int size() {
        return list.length;
    }

    protected int getQueueLength() {
        return queueLength();
    }

    int getBegin() {
        return begin;
    }

    protected int getEnd() {
        return end;
    }

    protected void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(list[i] + " ");
        }
        return sb.toString();
    }

    protected int getDEFAULT_CAPACITY() {
        return DEFAULT_CAPACITY;
    }

}
