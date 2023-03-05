import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DishSearch {

    public static void main(String[] args) {
        String filename = "D:\\УНИВЕР\\II курс\\ASDC\\Lab1\\src\\Menu.txt"; // имя файла с данными

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название блюда для поиска: ");
        String searchKey = scanner.nextLine(); // считываем строку с клавиатуры

        long startTime = System.currentTimeMillis(); // время начала поиска

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // разбиваем строку на поля
                String name = fields[0]; // первое поле - название блюда
                if (name.equals(searchKey)) { // если нашли нужное блюдо
                    System.out.println( fields[0] + ", " + fields[1] + ", " + fields[2] + ", " + fields[3] + ", " + fields[4]);

                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis(); // время окончания поиска

        System.out.println("Практическое время поиска: " + (endTime - startTime) + " мс");
    }

}
