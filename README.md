# Books shop 
## About
This project is a Provectus Test Task application for Internship 2020

## Installation
1. Install JDK 11 or later
2. Run your local MySQL server
    #### Enter commands below to create a required DB and user
        CREATE USER 'shop-dev'@'localhost' IDENTIFIED BY 'root';
        CREATE DATABASE shop_db;
        GRANT ALL ON shop_Db.* TO 'shop-dev'@'localhost';
3. Clone the repository
4. Enter the following commands at the repository folder by your OS:
    ### Linux/macOS
        ./mvnw clean install
        ./mvnw spring-boot:run -Pdev
    ### Windows 
        ./mvnw.cmd clean install
        ./mvnw spring-boot:run -Pdev
4. Assert **Home page** on http://localhost:8080/