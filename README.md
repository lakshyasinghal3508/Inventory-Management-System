# Inventory Management System

A command-line **Inventory Management System** developed using Core Java. The system allows users to manage product information, track stock levels, search and update products, generate basic inventory reports, and store inventory data using a plain text file.

The project is developed as part of the **Programming in Java** course and demonstrates practical application of Object-Oriented Programming, Collections, Exception Handling, File Handling, Input Validation, and modular Java programming.

---

## Features

The system provides the following features:

1. Add a new product.
2. Display all products.
3. Search for a product by exact ID or partial name.
4. Update product information.
5. Delete a product.
6. Add stock to an existing product.
7. Remove/sell stock from an existing product.
8. Display products at or below their minimum stock level.
9. Calculate the total value of the inventory.
10. Save product information to a file.
11. Load previously saved product information.
12. Validate user input and handle invalid operations.
13. Prevent duplicate product IDs.
14. Prevent negative price and quantity values.
15. Prevent removing more stock than is currently available.

---

## Major Functional Modules

### 1. Product Management

This module manages the basic information of products.

Functions include:

* Add Product
* View Products
* Search Product
* Update Product
* Delete Product

### 2. Stock Management

This module manages the quantity of products available in the inventory.

Functions include:

* Add Stock
* Remove Stock
* Check Stock
* Low-Stock Detection
* Out-of-Stock Detection
* Stock Validation

### 3. Reports and Inventory Analysis

This module provides useful information about the inventory.

Functions include:

* Total Number of Products
* Total Inventory Quantity
* Total Inventory Value
* Low-Stock Products
* Out-of-Stock Products
* Product Search and Filtering

### 4. File-Based Data Persistence

This module handles storage of inventory information.

Functions include:

* Save inventory data
* Load inventory data
* Handle file-related errors
* Preserve inventory information between sessions

---

## Technologies Used

* **Java**
* **Core Java**
* **Java Collections Framework**
* **Java I/O**
* **Exception Handling**
* **Custom Exceptions**
* **Input Validation**
* **Object-Oriented Programming**
* **Git and GitHub**

The project uses standard Java libraries and does not require external frameworks.

The application uses a **command-line interface** and file-based persistence rather than a graphical interface or relational database.

---

## Java Concepts Demonstrated

The project demonstrates the following Java concepts:

* Classes and Objects
* Encapsulation
* Constructors
* Access Modifiers
* Methods
* Java Collections Framework
* `ArrayList`
* Exception Handling
* Custom Exceptions
* File Handling
* Input Validation
* Searching
* Sorting/Filtering where applicable
* Modular Programming
* Packages where applicable

---

## Non-Functional Requirements

### Performance

The system should perform common inventory operations such as searching, adding, updating, and deleting products efficiently for a small-scale inventory.

### Usability

The command-line menus and messages should be clear and easy for users to understand.

### Reliability

The system should handle invalid operations and file-related problems without unexpectedly terminating during normal usage.

### Maintainability

The source code should be divided into meaningful classes with clear responsibilities so that future modifications can be made easily.

### Error Handling

Invalid user input, duplicate products, missing products, invalid stock operations, and file errors should be handled using appropriate validation and exception handling.

### Resource Efficiency

The system uses standard Java collections and file handling without requiring external frameworks or unnecessary system resources.

---

# System Design

## System Architecture Diagram

The application follows a simple layered structure:

```text
+-----------------------------+
|            USER             |
+--------------+--------------+
               |
               v
+-----------------------------+
|      Console / Main         |
|     User Interaction        |
+--------------+--------------+
               |
               v
+-----------------------------+
|    Inventory Management     |
|    Product & Stock Logic    |
+--------------+--------------+
               |
        +------+------+
        |             |
        v             v
+---------------+ +----------------+
| Report /      | | Exception &    |
| Validation    | | Error Handling |
+---------------+ +----------------+
        |
        v
+-----------------------------+
|       File Manager          |
|    Save / Load Inventory    |
+--------------+--------------+
               |
               v
+-----------------------------+
|       products.txt          |
|      File-Based Storage     |
+-----------------------------+
```

---

## System Workflow Diagram

```text
+-------+
| Start |
+---+---+
    |
    v
+----------------------+
| Load Saved Inventory |
+----------+-----------+
           |
           v
+----------------------+
| Display Main Menu    |
+----------+-----------+
           |
           v
+----------------------+
| Select an Operation  |
+----------+-----------+
           |
           v
+----------------------+
| Validate User Input  |
+----------+-----------+
           |
           v
+------------------------------+
| Perform Selected Operation   |
+--------------+---------------+
               |
               v
+------------------------------+
| Display Result / Error       |
+--------------+---------------+
               |
               v
        +------+------+
        |             |
        | Continue?   |
        |             |
        +------+------+
               |
        +------+------+
        |             |
       Yes           No
        |             |
        v             v
    Main Menu    Save Data
                      |
                      v
                    Exit
```

---

## Use Case Diagram

### Actor

**Inventory Manager / User**

### Main Use Cases

```text
                 +-----------------------------------+
                 | Inventory Management System       |
                 |                                   |
 User ---------->| Add Product                       |
 User ---------->| View Products                     |
 User ---------->| Search Product                    |
 User ---------->| Update Product                    |
 User ---------->| Delete Product                    |
 User ---------->| Add Stock                         |
 User ---------->| Remove Stock                      |
 User ---------->| Check Low Stock                   |
 User ---------->| Generate Inventory Information   |
 User ---------->| Save Inventory                    |
 User ---------->| Load Inventory                    |
 User ---------->| Exit System                       |
                 +-----------------------------------+
```

The user interacts with the system through the command-line interface to perform product, stock, reporting, and persistence operations.

---

## Class Diagram

The class relationships should follow the actual Java implementation.

```text
+---------------------------+
|          Product          |
+---------------------------+
| - productId               |
| - productName             |
| - category                |
| - price                   |
| - quantity                |
| - minimumStockLevel       |
+---------------------------+
| + getters/setters         |
| + toString()              |
+-------------+-------------+
              |
              |
              v
+---------------------------+
|        Inventory          |
+---------------------------+
| - products               |
+---------------------------+
| + addProduct()            |
| + displayProducts()       |
| + searchProduct()         |
| + updateProduct()         |
| + deleteProduct()         |
| + addStock()              |
| + removeStock()           |
| + lowStockProducts()      |
| + totalInventoryValue()   |
+-------------+-------------+
              |
              |
              v
+---------------------------+
|       FileManager         |
+---------------------------+
| + saveProducts()          |
| + loadProducts()          |
+---------------------------+

+---------------------------+
| DuplicateProductException |
+---------------------------+
| Custom Exception          |
+---------------------------+

+---------------------------+
|           Main            |
+---------------------------+
| Menu & User Interaction   |
+---------------------------+
```

> Note: The final class diagram should always be kept synchronized with the actual Java classes in the repository.

---

## Sequence Diagram — Add Product

```text
User
 |
 | Enter product details
 v
Main
 |
 | Send product information
 v
Inventory
 |
 | Check product ID
 |
 +--------------------------+
 | ID already exists?       |
 +------------+-------------+
              |
        +-----+-----+
        |           |
       Yes          No
        |           |
        v           v
 Duplicate       Create
 Exception       Product
        |           |
        v           v
 Display Error   Add Product
                    |
                    v
               Save / Update
                    |
                    v
                 FileManager
                    |
                    v
              products.txt
                    |
                    v
              Success Message
                    |
                    v
                   User
```

---

## Storage Design

The project uses **file-based persistence** rather than a relational database.

```text
+----------------------+
|      Product Object  |
+----------+-----------+
           |
           v
+----------------------+
|     Inventory        |
+----------+-----------+
           |
           v
+----------------------+
|     FileManager      |
+----------+-----------+
           |
           v
+----------------------+
|     products.txt     |
+----------------------+
```

The product information is stored in a plain text file and loaded when the application starts.

This approach keeps the project simple and demonstrates Java file-handling concepts without requiring an external database.

---

# Project Structure

```text
InventoryManagementSystem/
│
├── src/
│   ├── Main.java
│   ├── Product.java
│   ├── Inventory.java
│   ├── FileManager.java
│   └── DuplicateProductException.java
│
├── data/
│   └── products.txt
│
├── docs/
│   └── diagrams/
│       ├── system-architecture.png
│       ├── workflow-diagram.png
│       ├── use-case-diagram.png
│       ├── class-diagram.png
│       ├── sequence-diagram.png
│       └── storage-design.png
│
├── README.md
├── statement.md
├── TESTING.md
├── PROJECT_REPORT.md
├── GITHUB_CHECKLIST.md
├── VITYARTHI_REQUIREMENTS_CHECKLIST.md
└── .gitignore
```

> If a diagram image is added to the repository, the Markdown diagram sections above can be replaced with the actual image links.

---

# Requirements

To run the project, you need:

* Java Development Kit (JDK) 11 or newer
* Command Prompt, PowerShell, Bash, or another terminal

The project was developed/tested using a modern JDK.

---

# Installation / Setup

### 1. Check Java Installation

Run:

```bash
java -version
javac -version
```

### 2. Clone the Repository

```bash
git clone https://github.com/lakshyasinghal3508/Inventory-Management-System.git
```

### 3. Enter the Project Directory

```bash
cd Inventory-Management-System
```

---

# How to Compile

From the project root directory:

```bash
javac -d bin src/*.java
```

This compiles the Java source files and stores the generated `.class` files inside the `bin` directory.

---

# How to Run

Run:

```bash
java -cp bin Main
```

The application will start and attempt to load previously saved inventory data from:

```text
data/products.txt
```

The program should be run from the project root directory because the data file path is relative to the working directory.

---

# Menu Explanation

| Option | Action                          |
| ------ | ------------------------------- |
| 1      | Add a new product               |
| 2      | Display all products            |
| 3      | Search for a product            |
| 4      | Update product information      |
| 5      | Delete a product                |
| 6      | Add stock                       |
| 7      | Remove/sell stock               |
| 8      | Display low-stock products      |
| 9      | Calculate total inventory value |
| 10     | Save inventory data             |
| 11     | Load inventory data             |
| 12     | Exit the application            |

---

# Input Validation

The system validates common user inputs, including:

* Empty product names
* Invalid numeric input
* Negative prices
* Negative quantities
* Duplicate product IDs
* Product IDs that do not exist
* Removing more stock than available
* Invalid menu choices

Meaningful messages are displayed when an invalid operation is attempted.

---

# Sample Input / Output

Example:

```text
===== INVENTORY MANAGEMENT SYSTEM =====
1. Add a new product
2. Display all products
3. Search for a product
4. Update product
5. Delete product
6. Add stock
7. Remove stock
8. Low-stock products
9. Total inventory value
10. Save
11. Load
12. Exit

Enter your choice: 1

Product ID: P1
Product name: Rice Bag 5kg
Category: Grocery
Price: 50
Quantity: 100
Minimum stock level: 20

Product added successfully.
```

Example duplicate-product validation:

```text
Product ID: P1
Product name: Another Product
Category: Grocery
Price: 40
Quantity: 10
Minimum stock level: 5

Error: A product with ID 'P1' already exists.
```

Example invalid numeric input:

```text
Price: abc
That's not a valid number. Try again.

Price: 50
```

---

# Testing

Testing is documented separately in:

```text
TESTING.md
```

The testing document covers normal operations, invalid input, duplicate products, stock operations, file operations, and error handling.

Testing should be performed on the compiled application rather than relying only on expected results.

---

# Screenshots

The following screenshots should be included in the final project documentation/report:

1. Main Menu
2. Add Product
3. Display Products
4. Search Product
5. Update Product
6. Stock Management
7. Low-Stock Report
8. Error Handling
9. Inventory Value / Report

Screenshots should represent actual execution of the final version of the application.

---

# Design Documentation

Detailed project design documentation is available in:

```text
PROJECT_REPORT.md
```

The report contains:

* Problem Statement
* Objectives
* Functional Requirements
* Non-Functional Requirements
* System Architecture
* Use Case Diagram
* Workflow Diagram
* Sequence Diagram
* Class Diagram
* Storage Design
* Design Decisions
* Implementation Details
* Screenshots / Results
* Testing Approach
* Challenges
* Learnings
* Future Enhancements
* References

---

# Limitations

The current system has some limitations:

* Data is stored in a plain text file.
* The system is designed for single-user command-line usage.
* There is no multi-user or concurrent-access support.
* There is no authentication system.
* There is no graphical user interface.
* There is no relational database.
* There is no undo functionality.
* Advanced transaction history is not currently implemented.

---

# Future Enhancements

Possible future improvements include:

* Add sorting and filtering options.
* Add CSV import/export.
* Add transaction history with timestamps.
* Add a graphical user interface.
* Replace text-file storage with a database using JDBC.
* Add automated unit testing using JUnit.
* Add user authentication and role-based access.
* Add detailed inventory transaction reports.

---

# Academic Relevance

This project is developed for the **Programming in Java** course.

It demonstrates the practical use of Java programming concepts to solve a real-world inventory management problem.

The project focuses on:

* Object-Oriented Programming
* Collections
* Exception Handling
* File Handling
* Input Validation
* Modular Programming
* Searching and Data Management

---

# Repository Documentation

The repository contains the following important documentation:

| File                                  | Purpose                                                        |
| ------------------------------------- | -------------------------------------------------------------- |
| `README.md`                           | Project overview, setup, usage, architecture and documentation |
| `statement.md`                        | Problem statement, scope, target users and high-level features |
| `TESTING.md`                          | Testing approach and test cases                                |
| `PROJECT_REPORT.md`                   | Detailed academic project report                               |
| `GITHUB_CHECKLIST.md`                 | GitHub submission checklist                                    |
| `VITYARTHI_REQUIREMENTS_CHECKLIST.md` | VITyarthi requirement verification                             |

---

# Author

**Lakshya Singhal**

B.Tech — Programming in Java

VIT Bhopal University

---

# License

This project is created for academic and educational purposes.
