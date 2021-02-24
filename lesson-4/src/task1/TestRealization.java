package task1;

import java.util.Iterator;

class TestRealization {
    LinkedListImpl<String> MyLinkedList = new LinkedListImpl<>();
    //TODO Если его определить здесь, то вызываться методы встроенного Iterator
    //Инициализировать нужно локально
    //Iterator<String> iter = mll.iterator();

    void run() {
        //Test1. Добавляем элементы в начало связанного списка
        test1();//OK
        //After Test1. Some elements have been inserted in the first: array length: 3
        //Lyba, Maria, Katia,

        //Test2. Добавляем элемент в конец связанного списка
        test2();//OK
        //After Test2. The last element have been removed: array length: 4
        //Lyba, Maria, Katia, Petia,

        //Test3. Удаляем элемент из начала связанного списка
        test3();//OK
        //Lyba
        //After Test3. The first element have been removed: array length: 3
        //Maria, Katia, Petia,

        //Test4. Удаляем элемент из конца связанного списка
        test4();//OK
        //Petia
        //After Test4. The last element have been removed: array length: 2
        //Maria, Katia,

        //Test5. Добавляем элементы в начало связанного списка
        test5();//OK
        //After Test5. The element have been inserted in the place with 1 index: array length: 3
        //Maria, Vasia, Katia,

        //Test6. Тест итератора. Выводим элементы связанного списка
        test6();//OK
        //***Test2.1. Using foreach...***
        //Maria true, Vasia true, Katia true,
        //***Test6.2. Using while...***
        //Maria true, Vasia true, Katia false,

        //Test7. Тест итератора. Удаляем элементы, начинающиеся на "V" из связанного списка
        //с помощью цикла while
        test7();//OK
        //After Test7. The element Vasia have been removed
        //Maria Katia

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
        Iterator<String> iter = MyLinkedList.iterator();
        System.out.println("\n***Test6. Trying to test iterator...***");
        System.out.println("***Test6.1. Using foreach...***");
        for (String s : MyLinkedList) {
            //здесь s - текущий элемент!
            System.out.print(s + " " + iter.hasNext() + ", ");
        }
        System.out.println();
        System.out.println("\n***Test6.2. Using while...***");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " " + iter.hasNext() + ", ");
        }
        System.out.println();
    }

    //Test7. Тест итератора. Удаляем элемент, начинающийся на "V" из связанного списка
    //с помощью цикла while
    private void test7() {
        Iterator<String> iter = MyLinkedList.iterator();
        System.out.println("\n***Test7. Trying to test iterator Using while...***");
        String str = "";
        while (iter.hasNext()) {
            str = (String) iter.next();
            if (str.startsWith("V")) {
                iter.remove();//удаляет текущий элемент, если он начинается с V
                System.out.println("After Test7. The element " + str + " have been removed");
                break;
            }
        }
        for (String s : MyLinkedList) {
            System.out.print(s + " ");
        }
        System.out.println();
    }


}
