package com.car;
//ParkingSystem.java

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingSystem {
 private List<ParkingSpace> parkingSpaces;
 private final int capacity = 100;
 private final double chargeRate = 2.0;
 private final Scanner scanner = new Scanner(System.in);

 public ParkingSystem() {
     this.parkingSpaces = new CopyOnWriteArrayList<>();
     for (int i = 0; i < capacity; i++) {
         parkingSpaces.add(new ParkingSpace());
     }
 }

 public void parkCar(String licensePlate) {
     ParkingSpace availableSpace = findAvailableSpace();
     if (availableSpace != null) {
         Car car = new Car(licensePlate);
         availableSpace.occupy(car);
         int slotNumber = availableSpace.getSlotNumber(parkingSpaces);
         System.out.println("Car with license plate '" + licensePlate + "' parked successfully in slot " + slotNumber + ".");
     } else {
         System.out.println("Sorry, the car park is full.");
     }
 }

 public double leaveCar(String licensePlate) {
     ParkingSpace occupiedSpace = findOccupiedSpaceByLicensePlate(licensePlate);

     if (occupiedSpace != null) {
         Car parkedCar = occupiedSpace.getParkedCar();

         long departureTime = System.currentTimeMillis();

         // Calculate time spent in hours (rounded up to the nearest hour)
         long timeSpent = (departureTime - occupiedSpace.getEntryTime() + 3600000 - 1) / 3600000;

         // Calculate charge
         double charge = timeSpent * chargeRate;

         // Vacate the parking space
         occupiedSpace.vacate();

         System.out.println("Car with license plate '" + parkedCar.getLicensePlate() + "' left successfully.");
         System.out.println("Time Spent: " + timeSpent + " hours");
         System.out.println("Charge: £" + charge);

         return charge;
     } else {
         System.out.println("Car with license plate '" + licensePlate + "' not found or not parked in an occupied space.");
         return 0.0;
     }
 }

 private synchronized ParkingSpace findAvailableSpace() {
     for (ParkingSpace space : parkingSpaces) {
         if (!space.isOccupied()) {
             return space;
         }
     }
     return null;  // No available space
 }

 private synchronized ParkingSpace findOccupiedSpaceByLicensePlate(String licensePlate) {
     for (ParkingSpace space : parkingSpaces) {
         if (space.isOccupied() && space.getParkedCar().getLicensePlate().equals(licensePlate)) {
             return space;
         }
     }
     return null;  // Car not found or not parked in an occupied space
 }

 public int getAvailableSpaces() {
     int count = 0;
     for (ParkingSpace space : parkingSpaces) {
         if (!space.isOccupied()) {
             count++;
         }
     }
     return count;
 }

 public int findCarSlotNumber(String licensePlate) {
     for (ParkingSpace space : parkingSpaces) {
         if (space.isOccupied() && space.getParkedCar().getLicensePlate().equals(licensePlate)) {
             return space.getSlotNumber(parkingSpaces);
         }
     }
     return -1;  // Car not found
 }

 public void findYourCar(String licensePlate) {
     int slotNumber = findCarSlotNumber(licensePlate);
     if (slotNumber != -1) {
         System.out.println("Your car with license plate '" + licensePlate + "' is parked in slot " + slotNumber + ".");
     } else {
         System.out.println("Car with license plate '" + licensePlate + "' not found in the parking system.");
     }
 }

 public void run() {
     while (true) {
         System.out.println("\n------ Parking System Menu ------");
         System.out.println("1. Park Car");
         System.out.println("2. Leave Car");
         System.out.println("3. Available Spaces Count");
         System.out.println("4. Find Your Car");
         System.out.println("0. Exit");
         System.out.print("Enter your choice: ");

         int choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 System.out.print("Enter license plate: ");
                 String licensePlate = scanner.next();
                 parkCar(licensePlate);
                 break;
             case 2:
                 System.out.print("Enter license plate of the car leaving: ");
                 String leavingCarPlate = scanner.next();
                 leaveCar(leavingCarPlate);
                 break;
             case 3:
                 int availableSpaces = getAvailableSpaces();
                 System.out.println("Available Spaces: " + availableSpaces);
                 break;
             case 4:
                 System.out.print("Enter your car's license plate: ");
                 String carToFind = scanner.next();
                 findYourCar(carToFind);
                 break;
             case 0:
                 System.out.println("Exiting Parking System. Thank you!");
                 System.exit(0);
                 break;
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
 }

 /*public static void main(String[] args) {
     ParkingSystem parkingSystem = new ParkingSystem();
     parkingSystem.run();
 }*/
}
