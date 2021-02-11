package homework.Task3;

import java.util.Arrays;

public class DequeueTest {
    int size = 8;
    int count = 1;
    DequeueImpl<Integer> myDeque = new DequeueImpl<>(size);

    void run(){
        //Test1. Добавляем элементы в начало очереди(слева при нормальном порядке)
        test1(2);//OK
        //1 2
        //After Test1. Inserting left: array length: 8
        //[null, null, null, 2, 1, null, null, null]

        //Test2. Добавляем элементы в конец очереди(справа при нормальном порядке)
        test2(1);//OK
        //3
        //After Test2. Inserting right: array length: 8
        //[null, null, null, 2, 1, 3, null, null]

        //Test3. Удаляем элементы из конца очереди(справа при нормальном порядке)
        test3();//OK
        //3
        //After Test3. Removing right: array length: 8
        //[null, null, null, 2, 1, null, null, null]

        //Test4. Удаляем элементы из начала очереди(слева при нормальном порядке)
        test4();//OK
        //2
        //After Test4. Removing left: array length: 8
        //[null, null, null, null, 1, null, null, null]

        //Test5. Добавляем элементы в начало очереди(слева при нормальном порядке) с зацикливанием
        //Увеличение массива при обратном порядке очереди
        test5();//OK
        //4 5 6 7 8 9 10 11
        //After Test5. Inserting left: array length: 18
        //[7, 6, 5, 4, 1, null, null, null, null, null, null, null, null, null, 11, 10, 9, 8]

        //Test6. Удаляем элементы из начала очереди(слева при обратном порядке) без зацикливания
        test6();//OK
        //11
        //After Test6. Removing left: array length: 18
        //[7, 6, 5, 4, 1, null, null, null, null, null, null, null, null, null, null, 10, 9, 8]

        //Test7. Удаляем элементы из конца очереди(справа при обратном порядке) без зацикливания
        test7();//OK
        //1
        //After Test7. Removing right: array length: 18
        //[7, 6, 5, 4, null, null, null, null, null, null, null, null, null, null, null, 10, 9, 8]

        //Test8. Добавляем элементы в конец очереди(справа при обратном порядке) с зацикливанием
        //Увеличение массива при обратном порядке очереди
        test8();//OK
        //12 13 14 15 16 17 18 19 20 21 22 23
        //After Test8. Inserting right: array length: 28
        //[7, 6, 5, 4, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, null, null, null, null, null, null, null, null, null, 10, 9, 8]

        //Test9. Удаляем элементы из конца очереди(справа при обратном порядке) без зацикливания
        test9();//OK
        //23 22 21 20 19 18 17 16 15 14 13 12 4 5 6 7 8 9 10
        //After Test9. Removing right: array length: 10
        //[null, null, null, null, null, null, null, null, null, null]

        //Test10. Последовательно наполняем массив полностью в прямом порядке без зацикливания(Test1, Test2).
        //Затем добавляем элемент в начало очереди(слева при нормальном порядке) (Test1)
        //Массив должен увеличить вместимость и скопировать элементы текущего массива в прямом порядке
        test10();//OK
        //After Test10. Filling array in right order: array length: 10
        //[33, 32, 31, 30, 29, 24, 25, 26, 27, 28]
        //34
        //After Test1. Inserting left: array length: 20
        //[null, null, null, null, 34, 33, 32, 31, 30, 29, 24, 25, 26, 27, 28, null, null, null, null, null]

    }

    //Test1. Добавляем элементы в начало очереди(слева при нормальном порядке)
    private void test1(int edge){
        System.out.println("***Test1. Trying to insert left in right order without looping...***");
        for (int i = 0; i < edge; i++) {
            myDeque.insertLeft(count++);
            System.out.print(myDeque.peekLeft() + " ");
        }
        System.out.println("\nAfter Test1. Inserting left: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test2. Добавляем элементы в конец очереди(справа при нормальном порядке)
    private void test2(int edge){
        System.out.println("\n***Test2. Trying to insert right in right order without looping...***");
        for (int i = 0; i < edge; i++) {
            myDeque.insertRight(count++);
            System.out.print(myDeque.peekRight() + " ");
        }
        System.out.println("\nAfter Test2. Inserting right: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test3. Удаляем элементы из конца очереди(справа при нормальном порядке)
    private void test3(){
        System.out.println("\n***Test3. Trying to remove right without looping...***");
        for (int i = 0; i < 1; i++) {
            System.out.print(myDeque.removeRight() + " ");
        }
        System.out.println("\nAfter Test3. Removing right: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test4. Удаляем элементы из начала очереди(слева при нормальном порядке)
    private void test4(){
        System.out.println("\n***Test4. Trying to remove left without looping...***");
        for (int i = 0; i < 1; i++) {
            System.out.print(myDeque.removeLeft() + " ");
        }
        System.out.println("\nAfter Test4. Removing left: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test5. Добавляем элементы в начало очереди(слева при нормальном порядке) с зацикливанием
    //Увеличение массива при обратном порядке очереди
    private void test5(){
        System.out.println("\n***Test5. Trying to insert left in right order with looping...***");
        System.out.println("With array increasing when the queue in reverse order case!");
        int edge = myDeque.size() - myDeque.getQueueLength() + 1;
        for (int i = 0; i < edge; i++) {
            myDeque.insertLeft(count++);
            System.out.print(myDeque.peekLeft() + " ");
        }
        System.out.println("\nAfter Test5. Inserting left: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test6. Удаляем элементы из начала очереди(слева при обратном порядке) без зацикливания
    private void test6(){
        System.out.println("\n***Test6. Trying to remove left in reverse order without looping...***");
        for (int i = 0; i < 1; i++) {
            System.out.print(myDeque.removeLeft() + " ");
        }
        System.out.println("\nAfter Test6. Removing left: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test7. Удаляем элементы из конца очереди(справа при обратном порядке) без зацикливания
    private void test7(){
        System.out.println("\n***Test7. Trying to remove right in reverse order without looping...***");
        for (int i = 0; i < 1; i++) {
            System.out.print(myDeque.removeRight() + " ");
        }
        System.out.println("\nAfter Test7. Removing right: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test8. Добавляем элементы в конец очереди(справа при обратном порядке) с зацикливанием
    //Увеличение массива при обратном порядке очереди
    private void test8(){
        System.out.println("\n***Test8. Trying to insert right in reverse order with looping...***");
        System.out.println("With array increasing when the queue in reverse order case!");
        int edge = myDeque.size() - myDeque.getQueueLength() + 1;
        for (int i = 0; i < edge; i++) {
            myDeque.insertRight(count++);
            System.out.print(myDeque.peekRight() + " ");
        }
        System.out.println("\nAfter Test8. Inserting right: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test9. Удаляем элементы из конца очереди(справа при обратном порядке) с зацикливанием
    //Массив должен вернуться к дефолтной вместимости, когда опустошится полностью
    private void test9(){
        System.out.println("\n***Test9. Trying to remove right in reverse order with looping...***");
        System.out.println("With array decreasing when the queue became empty!");
        int edge = (myDeque.getEnd() + 1) + (myDeque.size() - myDeque.getBegin());
        for (int i = 0; i < edge; i++) {
            int temp = myDeque.removeRight();
            //System.out.print(myDEK.removeRight() + " ");
            System.out.print(temp + " ");
        }
        System.out.println("\nAfter Test9. Removing right: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }

    //Test10. Последовательно наполняем массив полностью в прямом порядке без зацикливания(Test1, Test2).
    //Затем добавляем элемент в начало очереди(слева при нормальном порядке) (Test1)
    //Массив должен увеличить вместимость и скопировать элементы текущего массива в прямом порядке
    private void test10(){
        System.out.println("\n***Test10. Trying to insert left in right order without looping...***");
        System.out.println("With array increasing when the queue in reverse order case!");
        //наполняем массив справа в прямом порядке
        test2(5);
        //наполняем массив слева в прямом порядке
        test1(5);
        System.out.println("\nAfter Test10. Filling array in right order: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
        //наполняем массив слева в прямом порядке
        test1(1);

        System.out.println("\nAfter Test10. Inserting left: array length: " + myDeque.size());
        System.out.println(Arrays.toString(myDeque.getList()));
    }
}
