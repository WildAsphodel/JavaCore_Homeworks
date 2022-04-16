package Lesson7;
import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите имя города: ");
            String city = scanner.nextLine();

            System.out.println("Введите 1 для получения погоды на сегодня;" +
                    " Введите 5 для прогноза на 5 дней;" +  " Для выхода введите 0:");

            String command = scanner.nextLine();

            if (command.equals("0")) break;

            try {
                if (command.equals("1") || command.equals("5")) {
                controller.getWeather(command, city);}
                else {
                    System.out.println("Неккоректный символ!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
