package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

    static String patientPassword = "321";

    static String patientLogin = "Pat";

    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient(){
        System.out.print("Enter Patient Name: ");
        String name = scanner.next();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender: ");
        String gender = scanner.next();
        System.out.print("Enter Patient Height: ");
        int height = scanner.nextInt();
        System.out.print("Enter Patient Weight: ");
        double weight = scanner.nextDouble();
        System.out.print("Enter Patient Illness: ");
        String illness = scanner.next();
        System.out.print("Enter Patient Date of Birth: ");
        String dob = scanner.next();
        System.out.print("Enter Patient's Arrival Date: ");
        String arrival_date = scanner.next();
        try {
            String query = "insert into patients(name, age, gender, height, weight, illness, dob, arrival_date) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setInt(4, height);
            preparedStatement.setDouble(5, weight);
            preparedStatement.setString(6, illness);
            preparedStatement.setString(7, dob);
            preparedStatement.setString(8, arrival_date);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient added successfully!");
            }else{
                System.out.println("Failed to add Patient!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletePatient(){
        System.out.println("Enter the ID of the patient who will be deleted: ");
        int id = scanner.nextInt();
        String query = "delete from patients where id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient deleted successfully!");
            }else{
                System.out.println("Failed to delete Patient!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewPatients(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+--------------------+------+---------+--------+--------+------------+------------+--------------+");
            System.out.println("| Patient ID | Name               | Age  | Gender  | Height | Weight | Illness    | DoB        | Arrival Date |");
            System.out.println("+------------+--------------------+------+---------+--------+--------+------------+------------+--------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                int height = resultSet.getInt("height");
                double weight = resultSet.getDouble("weight");
                String illness = resultSet.getString("illness");
                String dob = resultSet.getString("dob");
                String arrival_date = resultSet.getString("arrival_date");
                System.out.printf("|%-12s|%-20s|%-6s|%-9s|%-8s|%-8s|%-12s|%-12s|%-14s|\n", id, name, age, gender, height, weight, illness, dob, arrival_date);
                System.out.println("+------------+--------------------+------+---------+--------+--------+------------+------------+--------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String query = "select * from patients where id=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void patientInformation(int id){
        String query = "select * from patients where id=?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println("Patient: ");
                System.out.println("+------------+--------------------+------+---------+--------+--------+------------+------------+--------------+");
                System.out.println("| Patient ID | Name               | Age  | Gender  | Height | Weight | Illness    | DoB        | Arrival Date |");
                System.out.println("+------------+--------------------+------+---------+--------+--------+------------+------------+--------------+");
                id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                int height = resultSet.getInt("height");
                double weight = resultSet.getDouble("weight");
                String illness = resultSet.getString("illness");
                String dob = resultSet.getString("dob");
                String arrival_date = resultSet.getString("arrival_date");
                System.out.printf("|%-12s|%-20s|%-6s|%-9s|%-8s|%-8s|%-12s|%-12s|%-14s|\n", id, name, age, gender, height, weight, illness, dob, arrival_date);
                System.out.println("+------------+--------------------+------+---------+--------+--------+------------+------------+--------------+");
            }else {
                System.out.println("Данные не найдены.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void patientLogging(Scanner scanner){

        boolean log = true;

        while(log) {
            System.out.print("Please enter the login of the patient: ");

            String login = scanner.next();

            System.out.print("Please enter the password of the " + login + ": ");

            String password = scanner.next();

            if (patientPassword.equals(password) && patientLogin.equals(login)) {
                System.out.println();
                System.out.println("+------------------------------------------+");
                System.out.println("|     Greetings, dear " + login + "!                 |");
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
