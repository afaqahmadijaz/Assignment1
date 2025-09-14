/*
 * Name: Afaq Ahmad
 * Description: Program to compute and display charges for a patient's hospital stay.
 *              Uses input validation and overloaded methods to calculate charges
 *              for inpatient and outpatient cases.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("***PATIENT'S HOSPITAL STAY CHARGES***");

        int patientState;
        // Validate patient type (must be 1 or 2)
        while (true) {
            System.out.print("\nPlease Type 1 for inpatient and 2 for outpatient: ");
            if (input.hasNextInt()) {
                patientState = input.nextInt();
                if (patientState == 1 || patientState == 2) break;
            } else {
                input.next();
            }
            System.out.println("\n!!!Please enter 1 or 2!!!");
        }

        switch(patientState) {
            case 1:
                System.out.println("\n***FOR INPATIENT STAY CHARGES***");
                // Collect inpatient data
                int ipHospitalDaysSpent = (int) inputValidation(input, "Please enter the number of days spent in the hospital (integer): ", true);
                double ipDailyRate = inputValidation(input, "Daily rate (room charges per day): ", false);
                double ipHospitalServiceCharges = inputValidation(input, "Charges for the hospital services i.e labs tests, etc: ", false);
                double ipHospitalMedicationCharges = inputValidation(input, "Hospital medication charges: ", false);

                double iptotal = calculateCharges(ipHospitalDaysSpent, ipDailyRate, ipHospitalServiceCharges, ipHospitalMedicationCharges);
                System.out.printf("\nTotal inpatient charges: $%.2f%n", iptotal);

                break;

            case 2:
                System.out.println("\n***FOR OUTPATIENT STAY CHARGES***");

                // Calculate outpatient charges
                double opHospitalServiceCharges = inputValidation(input, "Charges for the hospital services i.e labs tests, etc: ", false);
                double opHospitalMedicationCharges = inputValidation(input,"Hospital medication charges: ", false);

                double optotal = calculateCharges(opHospitalServiceCharges, opHospitalMedicationCharges);
                System.out.printf("\nTotal outpatient charges: $%.2f%n", optotal);

                break;
        }
        input.close();
    }
    public static double inputValidation (Scanner input, String prompt, boolean requireInteger) {
        while(true) {
            System.out.print(prompt);

            if (requireInteger) {
                if (input.hasNextInt()) {
                    int value = input.nextInt();
                    if (value < 0) {
                        System.out.println("\n!!!Value cannot be negative. Try again.!!!");
                    } else {
                        return value; // returned as double for shared use
                    }
                } else {
                    System.out.println("Invalid integer. Try again.");
                    input.next();
                }
            } else {
                if (input.hasNextDouble()) {
                    double value = input.nextDouble();
                    if (value < 0) {
                        System.out.println("\n!!!Value cannot be negative. Try again.!!!");
                    }
                    else {
                        return value;
                    }
                }
                else {
                    System.out.println("Invalid number. Try again.");
                    input.next();
                }
            }
        }
    }

    public static double calculateCharges (int days, double dailyRate, double services, double meds){
        return days * dailyRate + services + meds;
    }

    public static double calculateCharges (double services, double meds){
        return services + meds;
    }
}