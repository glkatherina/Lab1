import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class FibonacciSearch {

    // Метод Фибоначчи для поиска элемента в отсортированном массиве
    public static int fibonacciSearch(String fileName, String key) throws IOException {
        int fib1 = 0;
        int fib2 = 1;
        int fibM = fib1 + fib2;//формула Фибоначчи
        int n = countLines(fileName); // определяем количество строк в файле
        int offset = -1;

        while (fibM < n) {
            fib2 = fib1;
            fib1 = fibM;
            fibM = fib1 + fib2;
        }
        while (fibM > 1) {
            int i = Math.min(offset + fib2, n - 1);
            String line = readLine(fileName, i);
            int cmp = line.split(",")[0].compareTo(key);

            if (cmp < 0) {
                fibM = fib1;
                fib1 = fib2;
                fib2 = fibM - fib1;
                offset = i;

            } else if (cmp > 0) {
                fibM = fib2;
                fib1 = fib1 - fib2;
                fib2 = fibM - fib1;
            } else {

                return i;
            }
        }
        if (fib1 == 1 && offset < n - 1) {
            String line = readLine(fileName, offset + 1);
            if (line.split(",")[0].equals(key)) {
                return offset + 1;
            }
        }
        return -1; // не найдено
    }
    // Метод для подсчета количества строк в файле
    private static int countLines(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
    // Метод для чтения строки из файла по заданному индексу
    private static String readLine(String fileName, int index) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        for (int i = 0; i < index; i++) {
            reader.readLine();
        }
        String line = reader.readLine();
        reader.close();
        return line;
    }

    public static void main(String[] args) {
        String fileName = "D:\\УНИВЕР\\II курс\\ASDC\\Lab1\\src\\Menu1.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение для поиска:");
        String key = scanner.nextLine();
        scanner.close();
        try {
            int n = countLines(fileName);
            double expectedTime = calculateExpectedTime(n); // рассчитываем среднее теоретическое время работы
            long startTime = System.nanoTime(); // начинаем отсчет времени
            int index = fibonacciSearch(fileName, key);
            long endTime = System.nanoTime(); // заканчиваем отсчет времени
            double elapsedTime = (endTime - startTime) / 1_000_000.0; // переводим время из наносекунд в миллисекунды
            System.out.println("Практическое время поиска: " + elapsedTime + " мс");
            System.out.println("Среднее теоретическое время работы: " + expectedTime + " мс");
            if (index != -1) {
                String line = readLine(fileName, index);
                System.out.println(line);
            } else {
                System.out.println("Элемент не найден");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculateExpectedTime(int n) {
        return Math.log(n) / Math.log(1.618) * 1000; // формула для расчета среднего теоретического времени работы
    }

}
