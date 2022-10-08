import java.util.HashMap;

public class Shop {
    private final HashMap<ProductsType, Integer> products;
    private int balance = 2000;
    private int earned = 0;
    private int sold = 0;
    public Shop(HashMap<ProductsType, Integer> products) {
        this.products = products;
    }

    public int getEarned() {
        return earned;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getBalance() {
        return balance + earned;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public HashMap<ProductsType, Integer> getProducts() {
        return products;
    }

    public void addProduct(ProductsType productsType, int count) {
        if (products.containsKey(productsType)) {
            System.out.println("Такой продукт уже есть.");
        } else {
            setBalance(getBalance() - (productsType.getPrice() * count));
            products.put(productsType, count);
        }
    }
}
