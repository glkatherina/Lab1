import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SequentialSearch {

    private static final String FILE_NAME = "D:\\УНИВЕР\\II курс\\ASDC\\Lab1\\src\\Menu.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ключ для поиска: ");
        String key = scanner.nextLine();
        List<String[]> results = searchByKey(FILE_NAME, key, 0);
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            System.out.println("Результаты поиска:");
            for (String[] result : results) {
                for (String field : result) {
                    System.out.print(field + ", ");
                }
                System.out.println();
            }
        }
    }

    public static List<String[]> searchByKey(String fileName, String key, int keyIndex) {
        List<String[]> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            long startTime = System.currentTimeMillis(); // начало измерения времени
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[keyIndex].equalsIgnoreCase(key)) {
                    results.add(fields);
                }
            }
            long endTime = System.currentTimeMillis(); // конец измерения времени
            long timeElapsed = endTime - startTime; // время поиска
            System.out.println("Практическое время поиска: " + timeElapsed + " мс");
            int n = results.size(); // количество найденных элементов
            int m = 0;
            int fibM = 1;
            while (fibM < n) {
                int temp = fibM;
                fibM = fibM + m;
                m = temp;
            }
            long theoreticalTime = 2 + 4 * (n / fibM) + 1; // среднее теоретическое время работы
            System.out.println("Среднее теоретическое время работы: " + theoreticalTime + " ед.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }


}
