public class Thing {

    String name;
    int weight;
    int price;


    //Конструктор (имя, вес, цена)
    public Thing(String name, int weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    //Переопределяем String
    @Override
    public String toString() {
        return "\n\nПредмет: " + name + "\nВес: " + weight + " кг" + "\nЦена: " + price + " руб";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Thing thing = (Thing) o;

        if (weight != thing.weight) return false;
        if (price != thing.price) return false;
        return name != null ? name.equals(thing.name) : thing.name == null;
    }


    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        // result = 31 * result + weight;
        //result = 31 * result + price;
        result = result + weight;
        result = result + price;

        return result;
    }


}