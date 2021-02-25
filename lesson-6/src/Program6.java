public class Program6 {
    public static void main(String[] args) {
        final int min = -100;
        final int max = 100;
        int balance = 0;
        int notBalance = 0;
        for (int i = 0; i < 20; i++) {
            int a = 1;
            MyTreeMap<Integer, Integer> map = new MyTreeMap<>();
            while (map.height() != 6) {
                final int rnd = rnd(min, max);
                map.put(a, rnd);
                a++;
            }

            if (map.isBalanced()) {
                balance++;
            } else notBalance++;
        }

        System.out.println("balance: " + balance + " notBalanced: " + notBalance);
    }

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}