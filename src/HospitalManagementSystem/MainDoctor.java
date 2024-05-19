package HospitalManagementSystem;

import java.sql.Connection;
import java.util.Scanner;

public class MainDoctor {

    static String mainDoctorPassword = "321";

    static String mainDoctorLogin = "Main";

    private Connection connection;

    public MainDoctor(Connection connection) {
        this.connection = connection;
    }

    public static void mainDoctorLogging(Scanner scanner){

        boolean log = true;

        while(log) {
            System.out.print("Please enter the login of the mainDoctor: ");

            String login = scanner.next();

            System.out.print("Please enter the password of the " + login + ": ");

            String password = scanner.next();

            if (mainDoctorPassword.equals(password) && mainDoctorLogin.equals(login)) {
                System.out.println();
                System.out.println("+------------------------------------------+");
                System.out.println("|     Greetings, dear " + login + "!                |");
                System.out.println("+------------------------------------------+");
                log = false;
            }

            else {
                System.out.println("+------------------------------------------+");
                System.out.println("|Wrong password or login, please try again |");
                System.out.println("+------------------------------------------+");
            }
        }
        System.out.println();
    }
}
