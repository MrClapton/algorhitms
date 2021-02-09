import java.util.ArrayList;

public class TestDynamicArray {

    public static void main(String[] args) {
        Array<Integer> data = new ArrayImpl<>(4);
//        Array<Integer> data = new SortedArrayImpl<>();
                            //index:    data.remove(Integer.valueOf(251))                   data.remove(2)
        data.add(11);       //0                       0                                           0
        data.add(251);      //1                       X                                           X
        data.add(2948);     //2                       1                                           1
        data.add(12);       //3                       2                                           X
        data.add(128419);   //4                       3                                           2

        data.display();
        data.sortBubble();
        //data.sortSelect();
        //data.sortInsert();
        data.display();

        data.remove(Integer.valueOf(251));
        data.display();
        data.remove(2);
        data.display();

        System.out.println("data[2] = " + data.get(2));
        System.out.println("Find 2: " + data.contains(2948));
        System.out.println("Find 222: " + data.contains(222));


//        ArrayList<Integer> arrayList = new ArrayList<>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//
//        System.out.println(arrayList);
//
//        arrayList.remove((Integer)2);

    }
}
