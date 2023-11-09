package com.car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingSystemTest {
    private ParkingSystem parkingSystem;

    @BeforeEach
    public void setUp() {
        parkingSystem = new ParkingSystem();
    }

    @Test
    public void testParkCarPositive() {
        // Positive test case: Park a car successfully
        parkingSystem.parkCar("ABC123");
        // You can assert that the car is parked in a valid space, check the output, etc.
        // For example, assert that the available spaces count decreased by 1.
        assertEquals(99, parkingSystem.getAvailableSpaces());
    }

    @Test
    public void testParkCarNegativeFull() {
        // Negative test case: Try to park a car when the parking lot is full
        // Ensure it handles the full capacity scenario correctly
        for (int i = 0; i < 100; i++) {
            parkingSystem.parkCar("Car" + i);
        }
        // Attempt to park one more car
        parkingSystem.parkCar("OverflowCar");
        // You can assert that the car is not parked and check the output.
        // For example, assert that it prints a message indicating the parking lot is full.
        assertEquals(0, parkingSystem.getAvailableSpaces());
    }

    @Test
    public void testLeaveCarPositive() {
        // Positive test case: Leave a car successfully
        parkingSystem.parkCar("LeaveTestCar");
        double charge = parkingSystem.leaveCar("LeaveTestCar");
        // You can assert that the charge is calculated correctly, check the output, etc.
        assertEquals(2.0, charge);
    }

    @Test
    public void testLeaveCarNegativeNotFound() {
        // Negative test case: Try to leave a non-existent car
        double charge = parkingSystem.leaveCar("NonExistentCar");
        // You can assert that the charge is 0.0 and check the output.
        // For example, assert that it prints a message indicating the car was not found.
        assertEquals(0.0, charge);
    }

    @Test
    public void testLeaveCarNegativeNotOccupied() {
        // Negative test case: Try to leave an unoccupied space
        parkingSystem.parkCar("OccupiedCar");
        // Leave the car
        parkingSystem.leaveCar("OccupiedCar");
        // Try to leave the same car again
        double charge = parkingSystem.leaveCar("OccupiedCar");
        // You can assert that the charge is 0.0 and check the output.
        // For example, assert that it prints a message indicating the car was not occupied.
        assertEquals(0.0, charge);
    }

    @Test
    public void testFindYourCarPositive() {
        // Positive test case: Find a parked car
        parkingSystem.parkCar("FindTestCar");
        // Try to find the parked car
        parkingSystem.findYourCar("FindTestCar");
        // You can assert that the output indicates the correct slot number.
        // For example, assert that it prints a message like "Your car is parked in slot 1."
    }

    @Test
    public void testFindYourCarNegativeNotFound() {
        // Negative test case: Try to find a non-existent car
        // Try to find a car that hasn't been parked
        parkingSystem.findYourCar("NonExistentCar");
        // You can assert that the output indicates that the car was not found.
        // For example, assert that it prints a message like "Car not found in the parking system."
    }
}
