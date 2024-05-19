package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointments {
    private Connection connection;

    public Appointments(Connection connection) {
        this.connection = connection;
    }

    public void viewAppointments(){
        String query = "select * from appointments";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Appointments: ");
            System.out.println("+----+------------+-----------+------------------+");
            System.out.println("| id | patient_id | doctor_id | appointment_date |");
            System.out.println("+----+------------+-----------+------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int patient_id = resultSet.getInt("patient_id");
                int doctor_id = resultSet.getInt("doctor_id");
                String appointment_date = resultSet.getString("appointment_date");
                System.out.printf("|%-4s|%-12s|%-11s|%-18s|\n", id, patient_id, doctor_id, appointment_date);
                System.out.println("+----+------------+-----------+------------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

