# MyRide - Book Your Ride At Fare Price

## Overview

This is a Java-based Ride booking System that provides functionality for customers to book rides and for drivers to manage their rides. The system includes various vehicle types and user roles, allowing for a flexible and extendable framework. Using OOPs concepts.

## Features

- **Customer Management:**

  - Register customers
  - Request rides specifying pickup and drop-off locations
  - View available drivers for a location
  - Book rides with a selected driver

- **Driver Management:**

  - View and manage availability
  - Accept or reject ride requests
  - Provide fare rates based on vehicle type

- **Vehicle Management:**

  - Support for different vehicle types (AutoRickshaw, Bike, SedanCar, SuvCar)
  - Calculate fare rates and capacities

- **Ride Management:**

  - Book rides with time and location details
  - Assign drivers based on availability and cost

## Project Structure

```plaintext
src/
├── com/narola/krushit
│   ├── AutoRickshow.java
│   ├── Bike.java
│   ├── Car.java
│   ├── Customer.java
│   ├── Driver.java
│   ├── Ride.java
│   ├── SedanCar.java
│   ├── SuvCar.java
│   ├── User.java
│   ├── Vehicle.java
│   └── VehicleType.java
```

## Key Classes

### Customer

- Extends the `User` class
- Manages customer details and ride requests

### Driver

- Extends the `User` class
- Manages driver details, vehicle assignments, and ride confirmations

### Vehicle

- Abstract class representing a generic vehicle
- Extended by specific vehicle types (e.g., AutoRickshow, Bike, SedanCar, SuvCar)

### Ride

- Represents a ride with details like pickup location, drop-off location, customer, driver, and timings

### MyRideController

- Centralized controller to manage customers, drivers, and rides (not fully shown in the provided code)

## Future Enhancements

- Add a database for persistent storage of users, vehicles, and rides
- Implement a user-friendly GUI or REST API for easier interaction
- Introduce advanced ride allocation algorithms considering real-time traffic
- Add dynamic pricing based on demand and availability

---

Happy Coding!
