import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class InterpolationSearch {
    private static final int FIELD_COUNT = 5;
    private static final int RECORD_COUNT = 50;
    private static final String FILE_PATH = "D:\\УНИВЕР\\II курс\\ASDC\\Lab1\\src\\Menu1.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название блюда для поиска: ");
        String searchKey = scanner.nextLine();
        scanner.close();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            String[][] records = null;
            int recordCount = 0;

            while (line != null) {
                String[] fields = line.split(", ");
                if (records == null) {
                    records = new String[1][fields.length];
                } else if (recordCount >= records.length) {
                    records = Arrays.copyOf(records, records.length * 2);
                }
                records[recordCount] = fields;
                line = reader.readLine();
                recordCount++;
            }

            records = Arrays.copyOf(records, recordCount);

            // Сортировка массива по полю "название блюда"
            Arrays.sort(records, Comparator.comparing(record -> record[0]));

            long startTime = System.currentTimeMillis(); // сохраняем текущее время
            int[] indexes = interpolationSearch(records, searchKey);
            long endTime = System.currentTimeMillis(); // сохраняем текущее время после завершения поиска

            if (indexes.length > 0) {
                for (int index : indexes) {
                    String[] record = records[index];
                    System.out.println("Результаты поиска: " + record[0] + ", " + record[1] + ", " + record[2] + ", " + record[3] + ", " + record[4]);
                }
            } else {
                System.out.println("Ничего не найдено");
            }

            double averageTime = Math.log(Math.log(recordCount)); // среднее теоретическое время работы алгоритма
            System.out.println("Среднее теоретическое время работы алгоритма: " + averageTime);
            System.out.println("Практическое время поиска: " + (endTime - startTime) + " мс"); // выводим разницу между временами
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] interpolationSearch(String[][] records, String searchKey) {
        int low = 0;
        int high = records.length - 1;
        List<Integer> indexes = new ArrayList<>();

        // Расчет среднего теоретического времени работы
        double avgTime = Math.log(Math.log(records.length)) * (high - low);

        while (low <= high && searchKey.compareTo(records[low][0]) >= 0 && searchKey.compareTo(records[high][0]) <= 0) {
            int pos = low + ((high - low) * (searchKey.compareTo(records[low][0]))) / (searchKey.compareTo(records[high][0]));

            if (pos < 0 || pos >= records.length) { // проверка выхода за границы массива
                break;

            }

            if (searchKey.compareTo(records[pos][0]) == 0) {
                indexes.add(pos);
            }

            if (searchKey.compareTo(records[pos][0]) < 0) {
                high = pos - 1;
            } else {
                low = pos + 1;
            }
        }

        int[] result = new int[indexes.size()];
        for (int i = 0; i < indexes.size(); i++) {
            result[i] = indexes.get(i);
        }


        return result;
    }
}