package HospitalManagementSystem;

import com.mysql.cj.jdbc.Driver;

import javax.print.Doc;
import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    public static final String url = "jdbc:mysql://localhost:3306/hms";
    public static final String username = "root";
    public static  final String password = "1234";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection, scanner);
            Nurse nurse = new Nurse(connection, scanner);
            MainDoctor mainDoctor = new MainDoctor(connection);
            Instruction instruction = new Instruction(connection, scanner);

            while(true){
                System.out.println();
                System.out.println("====================================");
                System.out.println("    HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("====================================");
                System.out.println();
                System.out.println("FOR STARTING THE PROGRAM, PLEASE ENTER THE TYPE OF THE ACCOUNT: ");
                System.out.println("+--------------+");
                System.out.println("| MainDoctor   |");
                System.out.println("| Doctor       |");
                System.out.println("| Nurse        |");
                System.out.println("| Patient      |");
                System.out.println("+--------------+");
                System.out.println();
                System.out.print("Enter account type: ");
                String accountType = scanner.nextLine();
                accountType = accountType.toLowerCase();

                if (accountType.equals("maindoctor")) {
                    MainDoctor.mainDoctorLogging(scanner);
                    while(true) {
                        System.out.println("====================================");
                        System.out.println("    HOSPITAL MANAGEMENT SYSTEM");
                        System.out.println("====================================");
                        System.out.println(" ");
                        System.out.println("1-Doctors");
                        System.out.println("2-Patients");
                        System.out.println("3-Nurses");
                        System.out.println("4-Exit");
                        System.out.print("Select an Option: ");
                        int num = scanner.nextInt();
                        System.out.println(" ");
                        if(num == 1){
                            System.out.println("1. Add Doctor");
                            System.out.println("2. Delete Doctor");
                            System.out.println("3. View Doctors");
                            System.out.println("4. Best Doctor");
                            System.out.println("5. New Doctor");
                            System.out.println("6. Doctor's Schedule");
                            System.out.print("Choose one: ");
                            int choice = scanner.nextInt();
                            System.out.println(" ");
                            switch (choice) {
                                case 1:
                                    //Add Doctor
                                    doctor.addDoctor();
                                    System.out.println();
                                    break;
                                case 2:
                                    //Delete Doctor
                                    doctor.deleteDoctor();
                                    System.out.println();
                                    break;
                                case 3:
                                    //View Doctors
                                    doctor.viewDoctors();
                                    System.out.println();
                                    break;
                                case 4:
                                    doctor.bestDoctor();
                                    System.out.println();
                                    break;
                                case 5:
                                    doctor.newDoctor();
                                    System.out.println();
                                    break;
                                case 6:
                                    doctor.doctorsSchedule();
                                    System.out.println();
                                    break;
                            }
                        } else if (num == 2) {
                            System.out.println("1. Add Patient");
                            System.out.println("2. Delete Patient");
                            System.out.println("3. View Patients");
                            System.out.print("Choose one: ");
                            int choice = scanner.nextInt();
                            System.out.println(" ");
                            switch (choice) {
                                case 1:
                                    patient.addPatient();
                                    System.out.println();
                                    break;
                                case 2:
                                    patient.deletePatient();
                                    System.out.println();
                                    break;
                                case 3:
                                    patient.viewPatients();
                                    System.out.println();
                                    break;
                            }
                        } else if (num == 3) {
                            System.out.println("1. Add Nurse    | 4.Add Instruction");
                            System.out.println("2. Delete Nurse | 5.Delete Instruction");
                            System.out.println("3. View Nurses  | 6.View Instructions");
                            System.out.print("Choose one: ");
                            int choice = scanner.nextInt();
                            System.out.println(" ");
                            switch (choice) {
                                case 1:
                                    nurse.addNurse();
                                    System.out.println();
                                    break;
                                case 2:
                                    nurse.deleteNurse();
                                    System.out.println();
                                    break;
                                case 3:
                                    nurse.viewNurses();
                                    System.out.println();
                                    break;
                                case 4:
                                    instruction.addInstruction();
                                    System.out.println();
                                    break;
                                case 5:
                                    instruction.deleteInstruction();
                                    System.out.println();
                                    break;
                                case 6:
                                    instruction.viewInstructions();
                                    System.out.println();
                                    break;
                            }
                        } else if (num == 4) {
                            System.exit(0);
                        } else{
                            System.out.println("invalid option");
                        }
                    }
                } else if (accountType.equals("doctor")) {
                    Doctor.doctorLogging(scanner);
                    while(true) {
                        System.out.println("====================================");
                        System.out.println("    HOSPITAL MANAGEMENT SYSTEM");
                        System.out.println("====================================");
                        System.out.println(" ");
                        System.out.println("1-Patients");
                        System.out.println("2-Nurses");
                        System.out.println("3-Exit");
                        System.out.print("Select an Option: ");
                        int num = scanner.nextInt();
                        System.out.println(" ");
                        if (num == 1) {
                            System.out.println("1. Add Patient");
                            System.out.println("2. Delete Patient");
                            System.out.println("3. View Patients");
                            System.out.print("Choose one: ");
                            int choice = scanner.nextInt();
                            System.out.println(" ");
                            switch (choice) {
                                case 1:
                                    patient.addPatient();
                                    System.out.println();
                                    break;
                                case 2:
                                    patient.deletePatient();
                                    System.out.println();
                                    break;
                                case 3:
                                    patient.viewPatients();
                                    System.out.println();
                                    break;
                            }
                        } else if (num == 2) {
                            System.out.println("1. Add Nurse    | 4.Add Instruction");
                            System.out.println("2. Delete Nurse | 5.Delete Instruction");
                            System.out.println("3. View Nurses  | 6.View Instructions");
                            System.out.print("Choose one: ");
                            int choice = scanner.nextInt();
                            System.out.println(" ");
                            switch (choice) {
                                case 1:
                                    nurse.addNurse();
                                    System.out.println();
                                    break;
                                case 2:
                                    nurse.deleteNurse();
                                    System.out.println();
                                    break;
                                case 3:
                                    nurse.viewNurses();
                                    System.out.println();
                                    break;
                                case 4:
                                    instruction.addInstruction();
                                    System.out.println();
                                    break;
                                case 5:
                                    instruction.deleteInstruction();
                                    System.out.println();
                                    break;
                                case 6:
                                    instruction.viewInstructions();
                                    System.out.println();
                                    break;
                            }
                        } else if (num == 3) {
                            System.exit(0);
                        } else{
                            System.out.println("invalid option");
                        }
                    }
                }else if (accountType.equals("nurse")) {
                    Nurse.nurseLogging(scanner);
                    while(true){
                        System.out.println("====================================");
                        System.out.println("    HOSPITAL MANAGEMENT SYSTEM");
                        System.out.println("====================================");
                        System.out.println();
                        System.out.println("1. View Patients");
                        System.out.println("2. View Instructions");
                        System.out.println("3. Find Patient(by ID)");
                        System.out.println("4. Exit");
                        System.out.print("Choose one: ");
                        int choice = scanner.nextInt();
                        System.out.println(" ");
                        switch (choice) {
                            case 1:
                                patient.viewPatients();
                                System.out.println();
                                break;
                            case 2:
                                instruction.viewInstructions();
                                System.out.println();
                                break;
                            case 3:
                                System.out.print("Enter patient ID: ");
                                int id = scanner.nextInt();
                                boolean found = patient.getPatientById(id);
                                System.out.println("Patient found: " + found);
                                System.out.println();
                                break;
                            case 4:
                                System.exit(0);
                        }
                    }
                }else if (accountType.equals("patient")) {
                    Patient.patientLogging(scanner);
                    while(true){
                        System.out.println("====================================");
                        System.out.println("    HOSPITAL MANAGEMENT SYSTEM");
                        System.out.println("====================================");
                        System.out.println();
                        System.out.println("1. My Information");
                        System.out.println("2. Doctor's Schedule");
                        System.out.println("3. Exit");
                        System.out.print("Choose one: ");
                        int choice = scanner.nextInt();
                        System.out.println(" ");
                        switch (choice) {
                            case 1:
                                System.out.print("Enter your ID: ");
                                int id = scanner.nextInt();
                                patient.patientInformation(id);
                                System.out.println();
                                break;
                            case 2:
                                doctor.doctorsSchedule();
                                System.out.println();
                                break;
                            case 3:
                                System.exit(0);
                        }
                    }
                }else {
                    System.out.println("invalid account type");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
