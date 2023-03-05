import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BinarySearch {

    private static final String FILE_NAME = "D:\\УНИВЕР\\II курс\\ASDC\\Lab1\\src\\Menu1.txt";

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
            List<String[]> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line.split(","));
            }
            int low = 0;
            int high = list.size() - 1;
            long startTime = System.currentTimeMillis();
            while (low <= high) {
                int mid = (low + high) >>> 1;
                String[] fields = list.get(mid);
                int cmp = fields[keyIndex].compareToIgnoreCase(key);
                if (cmp == 0) {
                    results.add(fields);
                    break;
                } else if (cmp < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Время поиска: " + (endTime - startTime) + " мс");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

}
