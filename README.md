# Inventory Management System

A command-line Inventory Management System written in core Java. It lets a user
add, search, update, delete, and track stock levels for products, with all data
saved to and loaded from a plain text file so nothing is lost between sessions.

## Features

1. Add a new product
2. Display all products
3. Search for a product (by exact ID, or partial name match)
4. Update product information (name, category, price, minimum stock level)
5. Delete a product
6. Add stock to an existing product
7. Remove/sell stock from an existing product
8. Display all products currently at or below their minimum stock level
9. Calculate the total value of the entire inventory (price × quantity, summed)
10. Save product information to a file
11. Load saved product information from a file
12. Exit safely (with a reminder to save if changes were made)

## Technologies Used

- Java (developed and tested on JDK 21; compatible with any modern JDK)
- Standard Java libraries only: `java.util.ArrayList`, `java.util.Scanner`,
  `java.io.FileReader`, `java.io.FileWriter`, `java.io.BufferedReader`
- No external frameworks or libraries
- No GUI — terminal/command-line interface only
- No database — persistence is handled with a plain text file

## Requirements

- A Java Development Kit (JDK), version 11 or newer, installed on your machine
- A terminal (Command Prompt, PowerShell, Bash, or similar)

## Project Structure

```
InventoryManagementSystem/
├── src/
│   ├── Main.java                     -> Menu loop and user interaction
│   ├── Product.java                  -> Represents a single product
│   ├── Inventory.java                -> Core logic: add/search/update/delete/stock/value
│   ├── FileManager.java              -> Reads and writes the data file
│   └── DuplicateProductException.java -> Custom exception for duplicate IDs
├── data/
│   └── products.txt                  -> Created automatically when you first save
├── README.md
└── .gitignore
```

## Installation / Setup

1. Make sure a JDK is installed. Check with:
   ```
   java -version
   javac -version
   ```
2. Download or clone this project folder onto your machine.
3. Open a terminal and navigate into the project's root folder
   (the one containing the `src` folder), e.g.:
   ```
   cd InventoryManagementSystem
   ```

## How to Compile

From the project root folder, run:

```
javac -d bin src/*.java
```

This compiles all `.java` files in `src/` and places the resulting `.class`
files into a new `bin/` folder.

## How to Run

From the same project root folder, run:

```
java -cp bin Main
```

The program will start, attempt to load any previously saved data from
`data/products.txt`, and show the main menu.

> Note: the program expects to be run from the project's root folder, because
> it looks for `data/products.txt` relative to where it is started. If you run
> it from a different folder, it will simply create a fresh, empty inventory
> and a new `data/` folder in that location.

## Menu Explanation

| Option | Action |
|--------|--------|
| 1 | Prompts for ID, name, category, price, quantity, and minimum stock level, then adds the product. Rejects duplicate IDs, negative numbers, and empty names. |
| 2 | Lists every product currently in memory in a table. |
| 3 | Searches by exact ID first; if no exact match, searches product names for a partial (case-insensitive) match. |
| 4 | Lets you change a product's name, category, price, and/or minimum stock level. Leave a field blank to keep its current value. |
| 5 | Removes a product permanently by ID. |
| 6 | Increases a product's quantity (e.g. new shipment arrived). |
| 7 | Decreases a product's quantity (e.g. a sale). Refuses to remove more than is currently in stock. |
| 8 | Lists every product whose quantity is at or below its minimum stock level. |
| 9 | Prints the sum of (price × quantity) across every product. |
| 10 | Writes the current in-memory product list to `data/products.txt`, overwriting the file. |
| 11 | Reads `data/products.txt` and replaces the in-memory product list with what's in the file. |
| 12 | Exits the program. Reminds you to save first if you haven't. |

## Sample Input/Output

The following is real output from running the compiled program (not fabricated):

```
===== INVENTORY MANAGEMENT SYSTEM =====
1.  Add a new product
2.  Display all products
...
12. Exit
Enter your choice: 1
Product ID: P1
Product name: Rice Bag 5kg
Category: Grocery
Price: 50
Quantity: 100
Minimum stock level: 20
Product added successfully.

Enter your choice: 2
ID       Name                 Category        Price       Quantity   MinStock   Status
P1       Rice Bag 5kg         Grocery         $50.00      100        20

Enter your choice: 1
Product ID: P1
Product name: Another Product
Category: Grocery
Price: 40
Quantity: 10
Minimum stock level: 5
Error: A product with ID 'P1' already exists.
```

Invalid numeric input is handled by re-prompting rather than crashing:

```
Price: abc
That's not a valid number. Try again.
Price: 50
```

## Testing

See `TESTING.md` (or the Testing section of the project report) for the full
list of test cases and their actual, observed results. Every test listed there
was run against the compiled program during development — none of the results
were assumed or guessed.

## Limitations

- Data is stored in a plain text file with no encryption and no protection
  against manual editing/corruption outside of basic line-skipping on load.
- No multi-user or concurrent-access support; it's a single-user, single-session
  terminal tool.
- No undo function — deletions and stock changes are immediate.
- Product IDs are treated as plain text with no enforced format (e.g. no
  requirement that they start with a letter or follow a specific pattern).
- No sorting or filtering options beyond search and the low-stock view.

## Possible Future Improvements

- Sort/filter the product display (by category, by price, by quantity).
- Support CSV export/import for easier use in spreadsheet software.
- Add a transaction log (history of stock changes with timestamps).
- Replace the text file with a lightweight embedded database (e.g. SQLite)
  once the student is comfortable with JDBC.
- Add unit tests using JUnit instead of manual testing.
