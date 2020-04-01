# Books shop 
## About
This project is a Provectus Test Task application for Internship 2020

## Customer requirements:
   You need to create web-interface for admin for add/edit title, description of the book, price, author and genre.
   Internet user should be able to:
   1) See list of the books by different genres. Any book can belong to more that one genre.
   2) See list of the books by different authors. Any book can belong to more that one author.
   3) See book details(Whole information on book: title, description, list of genres, list of authors, price)
   4) On the same page user should be able to submit book order form: First Name, Last Name, Address, quantity of books user would like to order. This form should be stored in Database.
   #####Additional details:
   a) ~~Access to admin web-interface should be implemented via .htaccess,~~ You don't need to store Admin user and pass at Database
   
   b) Genres are single-level. No sub-genres.


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