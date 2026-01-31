# 🚗 Car Pooling Application (Java + MySQL)

A console-based Car Pooling application developed using **Core Java**, **JDBC**, and **MySQL**.
This project allows users to register, publish rides, search rides, book seats, and manage bookings.



## 📌 Features

- User Registration & Login
- Publish Ride (Source, Destination, Seats, Fare)
- View All Available Rides
- Search Ride by Source & Destination
- Book Ride (Seat-based booking)
- Cancel Ride (Owner)
- View My Rides
- Cancel Booking
- MySQL Database Integration

---

## 🛠️ Technologies Used

- Java (Core Java)
- JDBC
- MySQL
- VS Code
- MySQL Connector/J

---

## 📂 Project Structure

Car pooling/
│
├── srs/
│ ├── dao/
│ ├── model/
│ ├── service/
│ ├── db/
│ └── Main.java
│
├── lib/
│ └── mysql-connector-j-8.0.33.jar
│
├── README.md
└── .gitignore

---

## 🗄️ Database Setup

```sql
CREATE DATABASE cab_booking;
USE cab_booking;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50)
);

CREATE TABLE rides (
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT,
    source VARCHAR(50),
    destination VARCHAR(50),
    seats INT,
    fare_per_seat INT,
    status VARCHAR(10)
);

CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ride_id INT,
    user_id INT,
    seats_booked INT,
    total_fare INT
);

▶️ How to Run the Project

1️⃣ Compile
javac -encoding UTF-8 *.java

2️⃣ Run
java -cp ".;..\lib\mysql-connector-j-8.0.33.jar" Main

👨‍💻 Author
Tushar Kumar Ladia

⭐ Future Enhancements

GUI (JavaFX / Swing)
Password Authentication
Online Payment Integration
Ride Rating System

---

## ✅ STEP 4: Save file
Press:
Ctrl + S

---

## 🔥 STEP 5: GitHub ke liye ready

```bash
git add README.md
git commit -m "Added project README"
git push
