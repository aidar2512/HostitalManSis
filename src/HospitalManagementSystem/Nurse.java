package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Nurse {

    static String nursePassword = "321";

    static String nurseLogin = "Nur";

    private Connection connection;
    private Scanner scanner;

    public Nurse(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }


    public void addNurse(){
        System.out.print("Enter Nurse Name: ");
        String name = scanner.next();
        System.out.print("Enter Nurse Salary: ");
        int salary = scanner.nextInt();
        System.out.print("Enter Nurse's started day: ");
        String date_started = scanner.next();
        try {
            String query = "insert into nurses(name, salary, date_started) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, salary);
            preparedStatement.setString(3, date_started);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Nurse added successfully!");
            }else{
                System.out.println("Failed to add Nurse!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteNurse(){
        System.out.println("Enter the ID of the nurse who will be deleted: ");
        int id = scanner.nextInt();
        String query = "delete from nurse where id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Nurse deleted successfully!");
            }else{
                System.out.println("Failed to delete nurse!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewNurses(){
        String query = "select * from nurses";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Nurses: ");
            System.out.println("+------------+--------------------+---------------+--------------+");
            System.out.println("| Nurse id  | Name               | Salary$$$     | Date_started |");
            System.out.println("+------------+--------------------+---------------+--------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                String date_started = resultSet.getString("date_started");
                System.out.printf("|%-12s|%-20s|%-15s|%-14s|\n", id, name, salary, date_started);
                System.out.println("+------------+--------------------+---------------+--------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void nurseLogging(Scanner scanner){

        boolean log = true;

        while(log) {
            System.out.print("Please enter the login of the nurse: ");

            String login = scanner.next();

            System.out.print("Please enter the password of the " + login + ": ");

            String password = scanner.next();

            if (nursePassword.equals(password) && nurseLogin.equals(login)) {
                System.out.println();
                System.out.println("+------------------------------------------+");
                System.out.println("|     Greetings, dear " + login + "!              |");
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
