package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    static String doctorPassword = "321";

    static String doctorLogin = "Doc";

    private Connection connection;
    private Scanner scanner;

    public Doctor(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }


    public void addDoctor(){
        System.out.print("Enter Doctor Name: ");
        String name = scanner.next();
        System.out.print("Enter Doctor Specialization: ");
        String specialization = scanner.next();
        System.out.print("Enter Doctor Salary: ");
        int salary = scanner.nextInt();
        System.out.print("Enter Doctor's started day: ");
        String date_started = scanner.next();
        try {
            String query = "insert into doctors(name, specialization, salary, date_started) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, specialization);
            preparedStatement.setInt(3, salary);
            preparedStatement.setString(4, date_started);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Doctor added successfully!");
            }else{
                System.out.println("Failed to add Doctor!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteDoctor(){
        System.out.println("Enter the ID of the doctor who will be deleted: ");
        int id = scanner.nextInt();
        String query = "delete from doctor where id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Doctor deleted successfully!");
            }else{
                System.out.println("Failed to delete Doctor!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewDoctors(){
        String query = "select * from doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+------------------+---------------+--------------+");
            System.out.println("| Doctor id  | Name               | Specialization   | Salary$$$     | Date_started |");
            System.out.println("+------------+--------------------+------------------+---------------+--------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                int salary = resultSet.getInt("salary");
                String date_started = resultSet.getString("date_started");
                System.out.printf("|%-12s|%-20s|%-18s|%-15s|%-14s|\n", id, name, specialization, salary, date_started);
                System.out.println("+------------+--------------------+------------------+---------------+--------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void bestDoctor(){
        String query = "select * from doctors order by salary desc limit 1";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("The Best Doctor: ");
            System.out.println("+------------+--------------------+------------------+---------------+");
            System.out.println("| Doctor id  | Name               | Specialization   | Salary$$$     |");
            System.out.println("+------------+--------------------+------------------+---------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                int salary = resultSet.getInt("salary");
                System.out.printf("|%-12s|%-20s|%-18s|%-15s|\n", id, name, specialization, salary);
                System.out.println("+------------+--------------------+------------------+---------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void newDoctor(){
        String query = "select * from doctors order by salary asc limit 1";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("New Doctor: ");
            System.out.println("+------------+--------------------+------------------+---------------+");
            System.out.println("| Doctor id  | Name               | Specialization   | Salary$$$     |");
            System.out.println("+------------+--------------------+------------------+---------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                int salary = resultSet.getInt("salary");
                System.out.printf("|%-12s|%-20s|%-18s|%-15s|\n", id, name, specialization, salary);
                System.out.println("+------------+--------------------+------------------+---------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query = "select * from doctors where id=?";
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

    public void doctorsSchedule(){
        String query = "select * from schedule";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+----+----------+------------+------------+------------+------------+------------+");
            System.out.println("| ID | Doctor   | Monday     | Tuesday    | Wednesday  | Thursday   | Friday     |");
            System.out.println("+----+----------+------------+------------+------------+------------+------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String doctor = resultSet.getString("doctor");
                String mon = resultSet.getString("mon");
                String tues = resultSet.getString("tues");
                String wed = resultSet.getString("wed");
                String thur = resultSet.getString("thur");
                String fri = resultSet.getString("fri");
                System.out.printf("|%-4s|%-10s|%-12s|%-12s|%-12s|%-12s|%-12s|\n", id, doctor, mon, tues, wed, thur, fri);
                System.out.println("+----+----------+------------+------------+------------+------------+------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void doctorLogging(Scanner scanner){

        boolean log = true;

        while(log) {
            System.out.print("Please enter the login of the doctor: ");

            String login = scanner.next();

            System.out.print("Please enter the password of the " + login + ": ");

            String password = scanner.next();

            if (doctorPassword.equals(password) && doctorLogin.equals(login)) {
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

