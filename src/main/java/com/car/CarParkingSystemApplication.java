package com.car;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarParkingSystemApplication {

	public static void main(String[] args) {
		ParkingSystem parkingSystem = new ParkingSystem();
        parkingSystem.run();
	}

}
