package task2;

import task1.LinkedListImpl;

//import java.util.Random;

class TestMethods {
    //private Random random = new Random();
    LinkedListImpl<String> MyLinkedList = new LinkedListImpl<>();

    void run() {
        //Test1. Добавляем элементы в начало связанного списка
        test1();//OK
        //

        //Test2. Добавляем элемент в конец связанного списка
        test2();//
        //

        //Test3. Удаляем элемент из начала связанного списка
        test3();//
        //

        //Test4. Удаляем элемент из конца связанного списка
        test4();//
        //

        //Test5. Добавляем элементы в начало связанного списка
        test5();//
        //

        //Test6. Тест итератора. Выводим элементы связанного списка
        test6();//
        //

        //Как правило, итераторы содержат следующие методы:
        //● reset() — перемещение в начало списка;
        //● getCurrent() — получение элемента, на который указывает итератор;
        //● atEnd() — возвращает true , если итератор находится в конце списка;
        //● insertAfter() — вставка элемента после итератора;
        //● insertBefore() — вставка элемента до итератора;
        //● deleteCurrent() — удаление элемента в текущей позиции итератора.
    }

    //Test1. Добавляем элементы в начало связанного списка
    private void test1() {
        System.out.println("***Test1. Trying to insert first...***");
        MyLinkedList.insertFirst("Katia");
        MyLinkedList.insertFirst("Maria");
        MyLinkedList.insertFirst("Lyba");
        System.out.println("After Test1. Some elements have been inserted in the first: array length: " + MyLinkedList.size());
        System.out.println(MyLinkedList);
    }

    //Test2. Добавляем элемент в конец связанного списка
    private void test2() {
        System.out.println("\n***Test2. Trying to insert last...***");
        MyLinkedList.insertLast("Petia");
        System.out.println("After Test2. The last element have been removed: array length: " + MyLinkedList.size());
        System.out.println(MyLinkedList);

    }

    //Test3. Удаляем элемент из начала связанного списка
    private void test3() {
        System.out.println("\n***Test3. Trying to remove the first element...***");
        System.out.println(MyLinkedList.removeFirst());
        System.out.println("After Test3. The first element have been removed: array length: " + MyLinkedList.size());
        System.out.println(MyLinkedList);
    }

    //Test4. Удаляем элемент из конца связанного списка
    private void test4() {
        System.out.println("\n***Test4. Trying to remove the last element...***");
        System.out.println(MyLinkedList.removeLast());
        System.out.println("After Test4. The last element have been removed: array length: " + MyLinkedList.size());
        System.out.println(MyLinkedList);
    }

    //Test5. Добавляем элементы в начало связанного списка
    private void test5() {
        int index = 1;
        System.out.println("\n***Test5. Trying to insert in indexed place...***");
        MyLinkedList.insert("Vasia", 1);
        System.out.println("After Test5. The element have been inserted in the place with " +
                index + " index: array length: " + MyLinkedList.size());
        System.out.println(MyLinkedList);
    }

    //Test6. Тест итератора. Выводим элементы связанного списка
    private void test6() {
        System.out.println("\n***Test6. Trying to test iterator...***");
        for (String s : MyLinkedList) {
            System.out.print(s + " ");
        }
    }


}
