import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class compOfSortingMethods {

    private static int DEFAULT_CAPACITY = 10000;
    private static final int MAX_VALUE = 10_000;

    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {

        Supplier<Array<Integer>> constructor = ArrayImpl::new;
        //Array<Integer> array4Bubble = new ArrayImpl<>(DEFAULT_CAPACITY);
        //Random random = new Random();
        //Array<Integer> array4Select = new ArrayImpl<>(DEFAULT_CAPACITY);
        //Array<Integer> array4Insert = new ArrayImpl<>(DEFAULT_CAPACITY);
        Array<Integer> array4Bubble = createArray(constructor);
        Array<Integer> array4Select = createArray(constructor);
        Array<Integer> array4Insert = createArray(constructor);

        randomInitialize(array4Bubble, array4Select, array4Insert);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Runnable> tasks = List.of(
                measureTime(array4Bubble::sortBubble, "SortBubble"),
                measureTime(array4Select::sortSelect, "SortSelect"),
                measureTime(array4Insert::sortInsert, "SortInsert")
        );

        for (Runnable task : tasks) {
            task.run();
        }
        /**
         * Console output:
         *
        SortBubble took time: 640751 micros.
        SortSelect took time: 188452 micros.
        SortInsert took time: 228078 micros.
            Process finished with exit code 0
         */

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);


            //Копирование полученного случайного массива в две другие переменные, чтобы на вход методам были поданы одинаковые массивы.
        //array4Select = array4Bubble.copyFullArray((Integer<>) array4Select,(Integer<>) array4Bubble);
        //System.arraycopy(array4Bubble, 0, array4Select, 0, DEFAULT_CAPACITY);
        //System.arraycopy(array4Bubble, 0, array4Select, 0, array4Bubble.size());
        //System.arraycopy(array4Bubble, 0, array4Insert, 0, DEFAULT_CAPACITY);
        //System.out.println(array4Select.size());

        //System.out.println("На сортировку 'пузырьком' ушло " + array4Bubble.sortBubble() + " мс");
        //System.out.println("На сортировку 'выбором' ушло " + array4Select.sortSelect() + " мс");
        // System.out.println("На сортировку 'вставкой' ушло " + array4Insert.sortInsert() + " мс");



    }

    private static void randomInitialize(Array<Integer>... arrays) {
        Random random = new Random();
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            int value = random.nextInt(MAX_VALUE);
            for (Array<Integer> array : arrays) {
                array.add(value);
            }
        }
    }

    private static Runnable measureTime(Runnable action, String actionName) {
        return () -> {
            long startTime = System.nanoTime();
            action.run();
            long finishTime = System.nanoTime();
            long duration = finishTime - startTime;

            System.out.printf("%s took time: %d micros.%n", actionName, TimeUnit.NANOSECONDS.toMicros(duration));
        };
    }

    private static Array<Integer> createArray(Supplier<Array<Integer>> factory) {
        return factory.get();//new ArrayImpl()
    }
}
