public enum ProductsType {
    BREAD(1),
    CHEESE(2),
    SAUSAGE(3),
    CUCUMBER(2),
    TOMATO(2),
    BEEF(4),
    CABBAGE(2),
    KETCHUP(1),
    ONION(2),
    FISH(3);

    private final int price;

    public int getPrice() {
        return price;
    }

    ProductsType(int price) {
        this.price = price;
    }
}
