import java.util.HashMap;

public class HotDog {
    private final String name;
    private final HashMap<ProductsType, Integer> hotDog;

    public HotDog(String name, HashMap<ProductsType, Integer> hotDog) {
        this.name = name;
        this.hotDog = hotDog;
    }

    public String getName() {
        return name;
    }

    public HashMap<ProductsType, Integer> getHotDog() {
        return hotDog;
    }

}
