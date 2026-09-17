# Project Report: Inventory Management System

## 1. Cover Page

**Project Title:** Inventory Management System

**Course:** Programming in Java

**Project Type:** Command-Line Core Java Application

**Student Name:** Lakshya Singhal

**Technology Used:** Java

**Development Approach:** Object-Oriented Programming

**Data Storage:** Plain Text File

---

## 2. Introduction

Inventory management means keeping track of products, their prices, available quantities, and stock levels. Managing this information manually can become difficult when the number of products increases.

To solve this problem, I developed an **Inventory Management System using Core Java**. The application works through the command line and allows the user to add, view, search, update, and delete products.

The system also provides features for adding and removing stock, checking low-stock products, calculating the total value of the inventory, and saving/loading data from a local text file.

This project helped me apply important Java concepts such as classes and objects, encapsulation, `ArrayList`, exception handling, file handling, constructors, methods, loops, conditional statements, and input validation.

---

## 3. Problem Statement

Managing inventory manually using notebooks or simple spreadsheets can cause problems such as duplicate product entries, incorrect stock quantities, forgotten updates, and difficulty in finding products that are running low.

The main aim of this project is to develop a simple Java-based system that can store product information, manage stock, validate user input, identify low-stock products, calculate inventory value, and save data for future use.

The system is designed to provide these functions through an easy-to-use command-line interface.

---

## 4. Functional Requirements

The system provides the following functions:

### FR1: Add Product

The user can add a new product by entering its ID, name, category, price, quantity, and minimum stock level. Product IDs must be unique.

### FR2: Display Products

The system can display all products currently available in the inventory.

### FR3: Search Product

The user can search for a product using its ID or by entering part of the product name.

### FR4: Update Product

The user can update the name, category, price, and minimum stock level of an existing product.

### FR5: Delete Product

The user can delete an existing product by entering its product ID.

### FR6: Add Stock

The system allows the user to increase the quantity of an existing product.

### FR7: Remove Stock

The system allows the user to decrease product quantity. It does not allow the user to remove more stock than the available quantity.

### FR8: Low-Stock Detection

The system identifies products whose quantity is equal to or below their minimum stock level.

### FR9: Calculate Inventory Value

The system calculates the total value of all products using:

**Total Inventory Value = Σ (Price × Quantity)**

### FR10: Save Data

The user can save the current inventory data to a local text file.

### FR11: Load Data

Previously saved product data can be loaded from the local file.

### FR12: Exit

The user can exit the application safely using the exit option.

---

## 5. Non-Functional Requirements

### 5.1 Usability

The application uses a simple menu-driven command-line interface so that the user can easily understand and use the available options.

### 5.2 Reliability

The system checks user input and handles invalid operations without stopping the complete application.

### 5.3 Performance

The system performs the inventory operations efficiently for the small amount of data expected in this project.

### 5.4 Maintainability

The program is divided into different classes, with each class having a specific responsibility. This makes the code easier to understand and modify.

### 5.5 Error Handling

The application displays clear error messages for invalid input, duplicate product IDs, products that do not exist, insufficient stock, and file-related errors.

### 5.6 Resource Efficiency

The project uses standard Java libraries and a simple text file for storage, so it does not require an external database or server.

---

## 6. System Architecture

The project uses a simple layered structure. Different responsibilities are handled by different classes.

```text
+-----------------------------+
|          Main.java          |
|     Presentation Layer      |
|      Menu + User Input      |
+-------------+---------------+
              |
              v
+-----------------------------+
|        Inventory.java        |
|        Logic Layer           |
| Add/Search/Update/Delete     |
| Stock Management + Reports   |
+-------------+---------------+
              |
              v
+-----------------------------+
|         Product.java         |
|         Data Layer           |
| Product Information          |
| Validation + Calculations    |
+-------------+---------------+
              |
              v
+-----------------------------+
|       FileManager.java       |
|      Persistence Layer       |
|        Save / Load           |
+-------------+---------------+
              |
              v
+-----------------------------+
|      data/products.txt       |
|         File Storage         |
+-----------------------------+
```

### Main Components

**Main.java:**
Handles the menu, takes input from the user, and displays the output.

**Inventory.java:**
Handles the main inventory operations such as adding, searching, updating, deleting, and managing stock.

**Product.java:**
Represents an individual product and stores its ID, name, category, price, quantity, and minimum stock level.

**FileManager.java:**
Handles saving and loading product data from the text file.

**DuplicateProductException.java:**
A custom exception used when a product with an already existing ID is added.

---

## 7. Design Diagrams

### 7.1 Use Case Diagram

```text
                  +----------------------------+
                  | Inventory Management       |
                  | System                     |
                  |                            |
User -----------> | Add Product                |
User -----------> | View Products              |
User -----------> | Search Product             |
User -----------> | Update Product             |
User -----------> | Delete Product             |
User -----------> | Add Stock                  |
User -----------> | Remove Stock               |
User -----------> | Check Low Stock            |
User -----------> | Calculate Inventory Value  |
User -----------> | Save Data                  |
User -----------> | Load Data                  |
User -----------> | Exit System                |
                  +----------------------------+
```

The user interacts with the application through the command-line menu and can select the required inventory operation.

---

### 7.2 Workflow Diagram

```text
              START
                |
                v
       Create Inventory
                |
                v
        Load Existing Data
                |
                v
          Display Menu
                |
                v
        Read User Choice
                |
                v
       Perform Operation
                |
                v
        Validate Operation
           /          \
        Valid        Invalid
          |             |
          v             v
      Show Result    Show Error
          |             |
          +------+------+
                 |
                 v
            Show Menu
                 |
                 v
           Exit Selected?
             /       \
           No         Yes
           |           |
           +----->     v
                     END
```

---

### 7.3 Sequence Diagram

```text
User        Main        Inventory       Product       FileManager
 |            |             |              |              |
 |--Add------>|             |              |              |
 |            |--addProduct>|              |              |
 |            |             |--create----->|              |
 |            |             |              |              |
 |            |<------------|              |              |
 |<--Success--|             |              |              |
 |            |             |              |              |
 |--Save----->|             |              |              |
 |            |----------------------------------------->|
 |            |             |              |              |
 |<--Saved----|             |              |              |
```

---

### 7.4 Class Diagram

```text
+--------------------------------+
|            Product             |
+--------------------------------+
| - id: String                   |
| - name: String                 |
| - category: String             |
| - price: double                |
| - quantity: int                |
| - minStockLevel: int           |
+--------------------------------+
| + getStockValue()              |
| + isLowStock()                 |
| + toFileLine()                 |
| + fromFileLine()               |
+--------------------------------+
               |
               |
               v
+--------------------------------+
|           Inventory            |
+--------------------------------+
| - products: List<Product>      |
+--------------------------------+
| + addProduct()                 |
| + searchProduct()              |
| + updateProduct()              |
| + deleteProduct()              |
| + addStock()                   |
| + removeStock()                |
| + getLowStockProducts()        |
| + getTotalInventoryValue()     |
+--------------------------------+

+--------------------------------+
|          FileManager           |
+--------------------------------+
| + save()                       |
| + load()                       |
+--------------------------------+

+--------------------------------+
|             Main               |
+--------------------------------+
| + main()                       |
| + menu methods                 |
+--------------------------------+

+--------------------------------+
|    DuplicateProductException   |
+--------------------------------+
| Custom Exception               |
+--------------------------------+
```

---

### 7.5 ER / Storage Design

This project does not use a relational database. Product information is stored in a plain text file.

**Storage File:**

```text
data/products.txt
```

**Record Format:**

```text
id,name,category,price,quantity,minStockLevel
```

Example:

```text
P1,Rice Bag 5kg,Grocery,50,100,20
```

Each line represents one product.

Since the project uses a text file instead of a relational database, a traditional ER diagram is not applicable.

---

## 8. Design Decisions & Rationale

### 8.1 Using Core Java

The project was developed using Core Java because the submitted course is Programming in Java. This also makes it possible to demonstrate the basic Java concepts learned during the course.

### 8.2 Using Object-Oriented Programming

The project represents each product as an object using the `Product` class. Inventory-related operations are handled by the `Inventory` class. This makes the program more organized and demonstrates OOP concepts.

### 8.3 Using ArrayList

`ArrayList<Product>` is used for storing products. Since this is a small course-level project, `ArrayList` is sufficient and keeps the implementation simple.

### 8.4 Separating Classes

Different responsibilities are divided among different classes instead of putting all the code in `Main.java`. This makes the project easier to understand, test, and modify.

### 8.5 Using Text File Storage

A text file is used to save the inventory instead of a database. This keeps the application simple and also demonstrates Java file handling.

### 8.6 Using a Custom Exception

`DuplicateProductException` was created to handle duplicate product IDs. This demonstrates how custom exceptions can be created for specific application requirements.

### 8.7 Input Validation

Input validation is used to prevent invalid product information such as negative prices, negative quantities, empty IDs, or invalid stock operations.

---

## 9. Implementation Details

The project contains five main Java classes.

### 9.1 Product.java

The `Product` class represents one product in the inventory.

It stores:

* Product ID
* Product name
* Category
* Price
* Quantity
* Minimum stock level

The fields are private and are accessed through methods. Validation is performed when product data is created or changed.

The class also provides methods for calculating stock value and checking low-stock status.

---

### 9.2 Inventory.java

The `Inventory` class manages all products using an `ArrayList<Product>`.

It performs operations such as:

* Adding products
* Searching products
* Updating products
* Deleting products
* Adding stock
* Removing stock
* Finding low-stock products
* Calculating total inventory value

A linear search is used for finding products by ID because the expected number of products in this project is small.

---

### 9.3 FileManager.java

The `FileManager` class is responsible for storing and retrieving data.

It uses Java file handling classes such as:

* `FileWriter`
* `FileReader`
* `BufferedReader`

The product data is stored in:

```text
data/products.txt
```

The file contains one product record per line.

---

### 9.4 Main.java

The `Main` class contains the `main()` method and handles the command-line interface.

It displays the menu, takes input using `Scanner`, and calls the required methods.

A loop is used to keep the application running until the user selects the exit option.

A `switch` statement is used to process menu choices, while `try-catch` blocks are used for handling errors.

---

### 9.5 DuplicateProductException.java

This class is a custom exception created for duplicate product IDs.

When a user tries to add a product with an ID that already exists, the exception is generated and an appropriate message is shown.

---

### 9.6 Program Working

The basic working of the application is:

1. The program starts and creates an inventory object.
2. Existing data can be loaded from the text file.
3. The main menu is displayed.
4. The user selects an operation.
5. The required information is entered.
6. The corresponding inventory operation is performed.
7. Invalid input or operations are handled using validation and exceptions.
8. The result is displayed to the user.
9. The menu is displayed again.
10. The user can save the data or exit the application.

---

## 10. Screenshots / Results

The application produces different command-line results for different operations.

### Example: Adding a Product

```text
Enter your choice: 1
Product ID: P1
Product name: Rice Bag 5kg
Category: Grocery
Price: 50
Quantity: 100
Minimum stock level: 20

Product added successfully.
```

### Example: Calculating Inventory Value

```text
Enter your choice: 9

Total inventory value: $5000.00
```

The application also produces results for:

* Searching products
* Updating products
* Deleting products
* Adding stock
* Removing stock
* Checking low-stock products
* Saving data
* Loading data
* Handling invalid input

Actual screenshots can be added to the final PDF as supporting evidence if required or desired.

---

## 11. Testing Approach

Testing was performed by running the compiled Java application and checking whether the output matched the expected result.

A total of **17 test cases** were documented in `TESTING.md`.

The following operations were tested:

1. Adding a valid product
2. Detecting a duplicate product ID
3. Rejecting an invalid price
4. Rejecting an invalid quantity
5. Searching an existing product by ID
6. Searching using a partial product name
7. Handling a product that does not exist
8. Updating a product
9. Deleting an existing product
10. Handling deletion of a missing product
11. Adding stock
12. Removing stock
13. Rejecting removal of insufficient stock
14. Detecting low-stock products
15. Calculating total inventory value
16. Saving inventory data
17. Loading inventory data in a separate run

All 17 documented test cases were executed against the compiled program and passed.

---

## 12. Challenges Faced

During the development of the project, I faced several challenges.

### 12.1 Input Validation

One challenge was making sure that invalid values such as negative prices or quantities were not accepted. Validation was added to handle these cases.

### 12.2 Duplicate Product IDs

Every product needs a unique ID. To handle duplicate IDs properly, a custom `DuplicateProductException` was created.

### 12.3 Stock Management

The system needed to make sure that the user could not remove more stock than was available. A check was added before decreasing the quantity.

### 12.4 File Handling

Another challenge was saving and loading product data between different program runs. Java file I/O classes were used to implement this functionality.

### 12.5 Handling Errors

The application should not close whenever the user enters invalid information. Exception handling was used so that an error message is displayed and the user can continue using the program.

### 12.6 Keeping the Code Simple

Since this project is for a Java course, the code needed to be easy to understand and explain. Therefore, the functionality was divided into separate classes with clear responsibilities.

---

## 13. Learnings & Key Takeaways

This project helped me understand and apply several important Java concepts.

### 13.1 Object-Oriented Programming

I learned how classes and objects can be used to represent real-world entities such as products and inventory.

### 13.2 Encapsulation

I learned how private fields and controlled methods can be used to protect data and perform validation.

### 13.3 Collections

Using `ArrayList<Product>` gave me practical experience in storing and managing multiple objects dynamically.

### 13.4 Exception Handling

I learned how built-in exceptions and custom exceptions can be used to handle errors without stopping the complete application.

### 13.5 File Handling

The project helped me understand how Java can read and write data using file handling classes.

### 13.6 Modular Programming

Dividing the project into different classes made the code easier to understand and maintain.

### 13.7 Input Validation

I learned that validating user input is important for preventing incorrect data and maintaining the correct state of the application.

### 13.8 Practical Application of Java

Overall, the project helped me connect the Java concepts studied in the course with a practical problem.

---

## 14. Future Enhancements

The project can be improved further in the future.

1. A graphical user interface can be added to make the application easier to use.
2. A database can be used instead of a text file for storing larger amounts of data.
3. `HashMap<String, Product>` can be used for faster product ID searching.
4. CSV import and export can be added for easier data transfer.
5. A transaction or audit log can be added to record stock changes.
6. User login and different user roles can be introduced.
7. Sorting and advanced filtering options can be added.
8. JUnit tests can be added for automated testing.
9. More detailed inventory reports can be added.
10. The project can later be extended into a web-based inventory management application.

---

## 15. References

1. Oracle, **The Java Tutorials**
   https://docs.oracle.com/javase/tutorial/

2. Oracle, **Java SE Documentation**
   https://docs.oracle.com/en/java/javase/

3. Java Standard Library documentation for `ArrayList`, `Scanner`, `FileWriter`, `FileReader`, `BufferedReader`, exception handling, classes, objects, and file I/O.

---

## Conclusion

The Inventory Management System is a command-line application developed using Core Java and object-oriented programming concepts.

The system provides features such as product management, stock management, low-stock detection, inventory value calculation, input validation, exception handling, and file-based data storage.

The project helped me apply important Programming in Java concepts in a practical application. The code is divided into separate classes, which makes it easier to understand, test, and maintain.

Overall, the project provides a simple solution for small-scale inventory management while demonstrating the practical use of Java programming concepts.
