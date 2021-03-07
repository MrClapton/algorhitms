/**
 * Algorithms and data structures in Java.
 * Урок 8. Хеш-таблицы
 * Домашнее задание.
 * DONE 1. Реализовать метод цепочек для хеш-таблицы.
 */
public class MainHW_8 {
    public static void main(String[] args) {

        ChainingHashMap<Integer, String> map = new ChainingHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(12, "12");
        map.put(15, "15");

        System.out.println(map);
        System.out.println("map.get(155): " + map.get(155));
        System.out.println("map.delete(2): " + map.delete(2));
        System.out.println("map.delete(2) again: " + map.delete(2));
        System.out.println(map);
//        Random random = new Random();
//        for (int i = 0; i < 50; i++) {
//            map.put(random.nextInt(1000),"");
//        }
//        System.out.println(map);

    }
}