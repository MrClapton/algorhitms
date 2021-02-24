package task3;

public class TestQueue {
    //private Random random = new Random();
    private LinkedQueueImpl<String> queue = new LinkedQueueImpl<>();


    void run() {
        //Test1. Добавляем элементы в очередь
        test1();//OK
        //After Test1. The elements have been inserted: queue size: 3
        //[Ivan, Ann, Sofia]

        //Test2. Получаем элемент из очереди перед удалением
        test2();//OK
        //After Test2. The element: Ivan
        //[Ivan, Ann, Sofia]

        //Test3. Удаляем элемент из очереди
        test3();//OK
        //Ivan
        //After Test3. The element have been removed: queue size: 2
        //[Ann, Sofia]
    }

    //Test1. Добавляем элементы в очередь
    private void test1() {
        System.out.println("***Test1. Trying to insert some elements...***");
        queue.insert("Ivan");
        queue.insert("Ann");
        queue.insert("Sofia");
        System.out.println("After Test1. The elements have been inserted: queue size: " + queue.size());
        System.out.println(queue);
    }

    //Test2. Получаем элемент из очереди перед удалением
    private void test2() {
        System.out.println("\n***Test2. Trying to get an element before removing...***");
        System.out.println("After Test2. The element: " + queue.peek());
        System.out.println(queue);
    }

    //Test3. Удаляем элемент из очереди
    private void test3() {
        System.out.println("\n***Test3. Trying to remove an element...***");
        System.out.println(queue.remove());
        System.out.println("After Test3. The element have been removed: queue size: " + queue.size());
        System.out.println(queue);
    }
}
