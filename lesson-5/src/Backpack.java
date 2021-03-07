import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private final int maxWeight; // максимальная емкость рюкзака
    private int maxPrice; //максимизируемый параметр цены
    private List<Thing> bestThings = null;//при инициализации пустой

    public Backpack(int maxWeight) {
        this.maxWeight = maxWeight;
    }


    //Метод суммирования содержимого по массе
    private int calculateWeight(List<Thing> things) {
        int sumWeight = 0;
        for (Thing thing : things) {
            sumWeight += thing.weight;
        }
        return sumWeight;
    }

    //Метод суммирования общей стоимости
    private int calculatePrice(List<Thing> things) {
        int sumPrice = 0;
        for (Thing thing : things) {
            sumPrice += thing.price;
        }
        return sumPrice;
    }


    private void bestSet(List<Thing> things) {
        if (bestThings == null) {//Базовый критерий (
            if (calculateWeight(things) <= maxWeight) {
                bestThings = things; //целевому листу присваиваем входящий список вещей
                maxPrice = calculatePrice(things); //аналогично с ценой, присваиваем сумму
            }
        } else {//если вес меньше и сумма выше текущей
            if (calculateWeight(things) <= maxWeight && calculatePrice(things) > maxPrice) {
                bestThings = things;
                maxPrice = calculatePrice(things);
            }
        }
    }

    public void calcBestSet(List<Thing> things) {
        if (things.size() > 0) {
            bestSet(things);
        }

        for (int i = 0; i < things.size(); i++) {
            List<Thing> list = new ArrayList<>(things);
            list.remove(i);
            calcBestSet(list);
        }
    }

    public List<Thing> getBestSet() {
        return bestThings;
    }

    public int getBestPrice() {
        return maxPrice;
    }
}