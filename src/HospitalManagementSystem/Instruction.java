package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Instruction {
    private Connection connection;
    private Scanner scanner;

    public Instruction(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addInstruction(){
        System.out.print("Enter Instruction: ");
        String instruction = scanner.next();
        instruction += scanner.nextLine();
        try {
            String query = "insert into Instructions(instruction) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, instruction);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Instruction added successfully!");
            }else{
                System.out.println("Failed to add Instruction!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteInstruction(){
        System.out.println("Enter the ID of the instruction which will be deleted: ");
        int id = scanner.nextInt();
        String query = "delete from instruction where id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Instruction deleted successfully!");
            }else{
                System.out.println("Failed to delete instruction!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewInstructions(){
        String query = "select * from Instructions";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Instructions: ");
            System.out.println("+----------------+-----------------------------------------------------+");
            System.out.println("| Instruction id |                   Instructions                      |");
            System.out.println("+----------------+-----------------------------------------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String instruction = resultSet.getString("instruction");
                System.out.printf("|%-16s|%-53s|\n", id, instruction);
                System.out.println("+----------------+-----------------------------------------------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
