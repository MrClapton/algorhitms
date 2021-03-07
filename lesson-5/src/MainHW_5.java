import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainHW_5 {
    private static final int MAX_WEIGHT = 5;//максимальная грузоподъемность рюкзака

    public static void main(String[] args) {
        System.out.println("\nЗАДАНИЕ №1. Возведение в степень с помощью рекурсии");
        System.out.println(exp(3, -2));//0.1111
        System.out.println(exp(2, 4));//16
        System.out.println(exp(100, 0));//1
        System.out.println(exp(10, -3));//0,001
        System.out.println(exp(0, 500));//0
        //   System.out.println(exp(0, 0));//ошибка

        System.out.println("\nЗАДАНИЕ №2. Задача о рюкзаке\nИмеется рюкзак с ограниченной вместимостью по массе; " +
                "также имеется набор вещей с определенным весом и ценой.\n" +
                "Необходимо подобрать такой набор вещей, чтобы он помещался в рюкзаке и имел максимальную стоимость.");

//Список предметов
        List<Thing> list = new LinkedList<>();
        list.add(new Thing("Книга", 1, 600));
        list.add(new Thing("Бинокль", 2, 5000));
        list.add(new Thing("Аптечка", 4, 1500));
        list.add(new Thing("Ноутбук", 2, 40000));
        list.add(new Thing("Котелок", 1, 500));

        System.out.println("\nЧто можно положить в рюкзак: ");
        stop();
        System.out.println(list);
        stop();

        Backpack backpack = new Backpack(MAX_WEIGHT);
        System.out.println("\nОптимальная раскладка в рюкзак с максимальной грузоподъемностью " + MAX_WEIGHT + " кг");
        backpack.calcBestSet(list);
        list = backpack.getBestSet();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).name + " " + list.get(i).price + " руб " + list.get(i).weight + " кг");
        }

        System.out.println("Лучшая цена: " + backpack.getBestPrice() + " руб");
    }


    private static double exp(double number, int degree) {
        if (number == 0) {
            if (degree != 0)
                return 0;
            else
                throw new IllegalArgumentException("Ноль в нулевой степени не определен");
        }
        if (degree < 0) {
            return 1 / (number * exp(number, -degree - 1));
        } else if (degree > 0) {
            return number * exp(number, degree - 1);
        } else {
            return 1;
        }
    }


    static void stop() {
        Scanner userIn = new Scanner(System.in);
        System.out.print("\nНажмите ENTER");
        userIn.nextLine();
    }
}
