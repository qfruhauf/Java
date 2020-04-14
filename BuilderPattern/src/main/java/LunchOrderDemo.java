public class LunchOrderDemo {
    public static void main(String[] args) {

        LunchOrderBean.Builder builder = new LunchOrderBean.Builder();
        builder.bread("Wheat").condiments("Lettuce").dressing("Ranch").meat("Turkey");

        LunchOrderBean lunch = builder.build();

        LunchOrderBean lunch2 = new LunchOrderBean.Builder().bread("White").condiments("Tomato").dressing("Mayo").meat("Ham").build();

        IceCream icecream = new IceCream.Builder(5).flavor("Vanilla").toppings("Sprinkles").build();
    }
}
