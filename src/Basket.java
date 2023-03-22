import java.io.*;

public class Basket {
    private static String[] products;
    private int[] prices;
    private int[] amountOfProducts;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.amountOfProducts = new int[products.length];
    }

    int sum;

    public void addToCart(int productNum, int amount) {
        amountOfProducts[productNum] += amount;
    }

    public void printCart() {
        System.out.println("Ваша корзина:");
        for (int i = 0; i < amountOfProducts.length; i++) {
            if (amountOfProducts[i] > 0) {
                System.out.println(products[i] + " - " + amountOfProducts[i] + " шт., " + prices[i] + " руб. за шт., " + amountOfProducts[i] * prices[i] + " руб. в сумме");
                sum += amountOfProducts[i] * prices[i];
            }
        }
        System.out.println("Итого " + sum + " руб");
    }

    public void saveTxt(File textFile) throws IOException {
        try (FileWriter file = new FileWriter(textFile);
             PrintWriter out = new PrintWriter(file)) {
            for (String product : products) {
                out.print(product + " ");
            }
            out.println();
            for (int price : prices) {
                out.print(price + " ");
            }
            out.println();
            for (int amount : amountOfProducts) {
                out.print(amount + " ");
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(textFile))) {
            String[] lineOne = in.readLine().split(" ");
            String[] lineTwo = in.readLine().split(" ");
            int[] convertedLineTwo = new int[lineTwo.length];
            for (int i = 0; i < lineTwo.length; i++) {
                convertedLineTwo[i] = Integer.parseInt(lineTwo[i]);
            }
            String[] lineThree = in.readLine().split(" ");
            int[] convertedLineThree = new int[lineThree.length];
            for (int ii = 0; ii < lineThree.length; ii++) {
                convertedLineThree[ii] = Integer.parseInt(lineThree[ii]);
            }
            Basket result = new Basket(lineOne, convertedLineTwo);
            result.amountOfProducts = convertedLineThree;
            return result;
        }
    }
}