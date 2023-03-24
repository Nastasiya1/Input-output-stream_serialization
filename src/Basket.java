import java.io.*;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    private String[] products;
    private int[] prices;
    private int[] amountOfProducts;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.amountOfProducts = new int[products.length];
    }

    public void addToCart(int productNum, int amount) {
        amountOfProducts[productNum] += amount;
    }

    public void printCart() {
        int sum = 0;
        System.out.println("Ваша корзина:");
        for (int i = 0; i < amountOfProducts.length; i++) {
            if (amountOfProducts[i] > 0) {
                System.out.println(products[i] + " - " + amountOfProducts[i] + " шт., " + prices[i] + " руб. за шт., " + amountOfProducts[i] * prices[i] + " руб. в сумме");
                sum += amountOfProducts[i] * prices[i];
            }
        }
        System.out.println("Итого " + sum + " руб");
    }

    public void saveBin(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("basket.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(Main.basket);
        }
    }

    static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("basket.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (Basket) ois.readObject();
        }
    }
}