public class IceCream {
    private String flavor;
    private String toppings;
    private int cost;

    public static class Builder {
        private String flavor;
        private String toppings;
        private int cost;

        public Builder(int cost) {
            this.cost = cost;
        }

        public IceCream build() {
            return new IceCream(this);
        }

        public Builder flavor(String flavor) {
            this.flavor = flavor;
            return this;
        }

        public Builder toppings(String toppings) {
            this.toppings = toppings;
            return this;
        }

//        private Builder cost(int cost) {
//            this.cost = cost;
//            return this;
//        }
    }

    private IceCream(Builder builder) {
        this.flavor = builder.flavor;
        this.toppings = builder.toppings;
        this.cost = builder.cost;
    }
}
