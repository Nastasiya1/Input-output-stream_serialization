import java.io.*;
import java.util.Scanner;

public class Main {
    static Basket basket;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String[] products = {
                "Хлеб",
                "Яблоки",
                "Молоко",
                "Каша",
                "Мука",
                "Творог"};
        int[] prices = {
                60,
                55,
                70,
                90,
                80,
                100};
        int[] amount = new int[products.length];
        File file = new File("basket.bin");
        if (file.exists()) {
            basket = Basket.loadFromBinFile(file);
        } else {
            basket = new Basket(products, prices);
        }

        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + 1 + "." + " " + products[i] + " " + prices[i] + " руб/шт");
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            basket.saveBin(file);
        }
        basket.printCart();
    }
}