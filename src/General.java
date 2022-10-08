import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class General {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        HashMap<ProductsType, Integer> storage = new HashMap<>();
        Shop shop = new Shop(storage);
        int startedBalance = shop.getBalance();
        putInStorage(shop);
        while (true) {
            System.out.println("""
                    1. Выбрать из трёх рецептов.
                    2. Создать свой хот-дог.
                    3. Просмотреть информацию магазина.
                    4. Добавить продукт в магазин.
                    5. Выход.
                    """);
            switch (inputInt()) {
                case 1 -> {
                    HotDog hotDog;
                    HashMap<ProductsType, Integer> hotDogProducts = new HashMap<>();
                    System.out.println("""
                            1. Классический.
                            2. Мясной.
                            3. Рыбный.
                            """);
                    hotDog = defaultRecipes(hotDogProducts);
                    checkProductsInShopForHotDog(shop, hotDog);
                }
                case 2 -> {
                    HashMap<ProductsType, Integer> products = new HashMap<>();
                    HotDog hotDog = new HotDog("custom", products);
                    hotDog.getHotDog().put(ProductsType.BREAD, 2);
                    while (true) {
                        System.out.print("Введите ингридиент, для выхода введите \"хватит\": ");
                        for (int i = 0; i < ProductsType.values().length; i++) {
                            System.out.print(ProductsType.values()[i] + " ");
                        }
                        System.out.println();
                        String product = inputString();
                        if (product.equals("хватит")) {
                            checkProductsInShopForHotDog(shop, hotDog);
                            break;
                        }
                        for (int i = 0; i < ProductsType.values().length; i++) {
                            if (String.valueOf(ProductsType.values()[i]).equals(product)) {
                                System.out.println("Введите количество:");
                                int count = inputInt();
                                hotDog.getHotDog().put(ProductsType.values()[i], count);
                                break;
                            }
                            if (i == ProductsType.values().length - 1) {
                                System.out.println("Неверный ввод.");
                            }
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Начальный баланс: "
                            + startedBalance
                            + "$\nВыручка составляет: "
                            + shop.getBalance() + "$\n"
                            + "Проданно хот-догов: "
                            + shop.getSold() + "\n");
                    for (Map.Entry<ProductsType, Integer> entry : shop.getProducts().entrySet()) {
                        System.out.println(entry.getKey() + " осталось " + entry.getValue());
                    }
                    System.out.println();
                }
                case 4 -> {
                    System.out.print("Введите ингридиент, который хотите добавить:");
                    for (int i = 0; i < ProductsType.values().length; i++) {
                        System.out.print(ProductsType.values()[i] + " ");
                    }
                    String product = inputString();
                    for (int i = 0; i < ProductsType.values().length; i++) {
                        if (String.valueOf(ProductsType.values()[i]).equals(product)) {
                            ProductsType temp = ProductsType.values()[i];
                            System.out.println("Введите количество:");
                            int count = inputInt();
                            shop.getProducts().put(temp, shop.getProducts().get(temp) + count);
                            shop.setBalance(shop.getBalance() - (temp.getPrice() * count));
                            break;
                        }
                        if (i == ProductsType.values().length - 1) {
                            System.out.println("Неверный ввод.");
                        }
                    }
                }
                case 5 -> System.exit(1);
                default -> System.out.println("Неверный ввод\n");

            }
        }
    }

    private static void checkProductsInShopForHotDog(Shop shop, HotDog hotDog) {
        int cost = 0;
        for (Map.Entry<ProductsType, Integer> entryFromShop : shop.getProducts().entrySet()) {
            for (Map.Entry<ProductsType, Integer> entryFromHotDog : hotDog.getHotDog().entrySet()) {
                if (entryFromShop.getKey() == (entryFromHotDog.getKey())) {
                    if (entryFromShop.getValue() >= entryFromHotDog.getValue()) {
                        entryFromShop.setValue(entryFromShop.getValue() - entryFromHotDog.getValue());
                        cost += (entryFromHotDog.getValue() * (entryFromHotDog.getKey().getPrice() + 1));
                    } else {
                        System.out.println("Не хватает " + entryFromShop.getKey() + "!\n");
                        break;
                    }
                }
            }
        }
        shop.setSold(shop.getSold() + 1);
        shop.setEarned(shop.getEarned() + cost);
    }

    private static HotDog defaultRecipes(HashMap<ProductsType, Integer> hotDogProducts) {
        HotDog hotDog;
        outLoop:
        while (true) {
            switch (inputInt()) {
                case 1 -> {
                    hotDogProducts.put(ProductsType.SAUSAGE, 2);
                    hotDogProducts.put(ProductsType.CABBAGE, 1);
                    hotDogProducts.put(ProductsType.BREAD, 2);
                    hotDogProducts.put(ProductsType.ONION, 1);
                    hotDogProducts.put(ProductsType.KETCHUP, 3);
                    hotDog = new HotDog("Классический", hotDogProducts);
                    break outLoop;
                }
                case 2 -> {
                    hotDogProducts.put(ProductsType.BEEF, 1);
                    hotDogProducts.put(ProductsType.CABBAGE, 1);
                    hotDogProducts.put(ProductsType.BREAD, 2);
                    hotDogProducts.put(ProductsType.TOMATO, 1);
                    hotDogProducts.put(ProductsType.KETCHUP, 3);
                    hotDogProducts.put(ProductsType.CHEESE, 2);
                    hotDog = new HotDog("Мясной", hotDogProducts);
                    break outLoop;
                }
                case 3 -> {
                    hotDogProducts.put(ProductsType.FISH, 1);
                    hotDogProducts.put(ProductsType.CABBAGE, 2);
                    hotDogProducts.put(ProductsType.BREAD, 2);
                    hotDogProducts.put(ProductsType.CUCUMBER, 2);
                    hotDogProducts.put(ProductsType.TOMATO, 2);
                    hotDog = new HotDog("Рыбный", hotDogProducts);
                    break outLoop;
                }
                default -> System.out.println("Неверный ввод\n");
            }
        }
        return hotDog;
    }

    private static void putInStorage(Shop shop) {
        shop.addProduct(ProductsType.BREAD, 100);
        shop.addProduct(ProductsType.ONION, 30);
        shop.addProduct(ProductsType.BEEF, 25);
        shop.addProduct(ProductsType.CABBAGE, 40);
        shop.addProduct(ProductsType.CHEESE, 60);
        shop.addProduct(ProductsType.CUCUMBER, 70);
        shop.addProduct(ProductsType.FISH, 30);
        shop.addProduct(ProductsType.TOMATO, 55);
        shop.addProduct(ProductsType.KETCHUP, 150);
        shop.addProduct(ProductsType.SAUSAGE, 120);
    }

    private static int inputInt() {
        while (true) {
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Неверный ввод");
            }
        }
    }

    private static String inputString() {
        while (true) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.out.println("Неверный ввод");
            }
        }
    }
}
