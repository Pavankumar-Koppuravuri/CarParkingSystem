package com.car;

//ParkingSpace.java

import java.util.List;

public class ParkingSpace {
 private boolean isOccupied;
 private long entryTime;  // Entry time in milliseconds
 private Car parkedCar;

 public ParkingSpace() {
     this.isOccupied = false;
     this.entryTime = 0;
     this.parkedCar = null;
 }

 public synchronized void occupy(Car car) {
     this.isOccupied = true;
     this.entryTime = System.currentTimeMillis();
     this.parkedCar = car;
 }

 public synchronized void vacate() {
     this.isOccupied = false;
     this.entryTime = 0;
     this.parkedCar = null;
 }

 public boolean isOccupied() {
     return isOccupied;
 }

 public long getEntryTime() {
     return entryTime;
 }

 public Car getParkedCar() {
     return parkedCar;
 }

 public int getSlotNumber(List<ParkingSpace> parkingSpaces) {
     return parkingSpaces.indexOf(this) + 1;
 }
}
