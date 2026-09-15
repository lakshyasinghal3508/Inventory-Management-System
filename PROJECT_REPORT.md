# Project Report: Inventory Management System

## Abstract

This project implements a command-line Inventory Management System using
core Java. The system allows a user to record products, track stock levels,
search and update product records, and persist all data to a local text
file between sessions. It was built using only standard Java libraries
(collections, I/O, exceptions) to keep the implementation understandable
for a student learning object-oriented programming, while still covering
practical software concerns such as input validation and data persistence.

## Introduction

Small businesses and individuals often need a simple way to track what
products they have, how many units are in stock, and what those products
are worth — without the overhead of a full point-of-sale system or database
server. This project addresses that need on a small scale, as a learning
exercise in applying object-oriented design, collections, exception
handling, and file I/O in Java.

## Problem Statement

Manually tracking inventory (e.g. on paper or in an unstructured spreadsheet)
is error-prone: duplicate entries, forgotten stock updates, and no easy way
to see which products are running low. The goal is to build a small program
that structures this data properly, validates input, and prevents common
mistakes (like selling more stock than is available).

## Objectives

1. Represent a product and its attributes as a well-encapsulated Java class.
2. Provide add, search, update, and delete operations on a collection of products.
3. Support stock increases (restocking) and decreases (sales), with checks
   against invalid amounts.
4. Flag products that have fallen to or below a defined minimum stock level.
5. Calculate the total monetary value of the current inventory.
6. Persist the inventory to disk so it survives between program runs.
7. Handle invalid user input gracefully, without crashing.

## Existing System

Many students track small inventories using spreadsheet software (e.g.
Excel/Google Sheets) or plain notebooks. These approaches have no built-in
validation (a spreadsheet will happily accept a negative quantity), no
programmatic enforcement of unique IDs, and require manual formulas to
calculate totals or spot low stock. Larger, real-world systems typically use
a relational database with a GUI or web front end, which is more capable
but also considerably more complex to build and explain.

## Proposed System

This project proposes a lightweight, terminal-based alternative built purely
in Java. It structures inventory data as objects, enforces validation rules
directly in code, and automates calculations (low-stock detection, total
value) that would otherwise require manual spreadsheet formulas. It uses
plain-text file storage rather than a database, which keeps the project
self-contained (no external server or driver needed) while still providing
real persistence.

## Functional Requirements

- FR1: The system shall allow adding a product with a unique ID, name,
  category, price, quantity, and minimum stock level.
- FR2: The system shall display all products currently stored.
- FR3: The system shall allow searching by product ID or partial product name.
- FR4: The system shall allow updating a product's name, category, price,
  and minimum stock level.
- FR5: The system shall allow deleting a product by ID.
- FR6: The system shall allow increasing a product's stock quantity.
- FR7: The system shall allow decreasing a product's stock quantity, and
  shall reject attempts to remove more than what is available.
- FR8: The system shall list all products at or below their minimum stock level.
- FR9: The system shall calculate the total value of all stock (price × quantity, summed).
- FR10: The system shall save all product data to a file on request.
- FR11: The system shall load previously saved product data from a file.
- FR12: The system shall allow the user to exit safely.

## Non-Functional Requirements

- The system must run entirely from the command line, with no GUI dependency.
- The system must use only the standard Java library — no third-party frameworks.
- The system must give clear, human-readable error messages for invalid input.
- The code must be simple enough for a student to explain line-by-line in a
  viva/defense setting.

## System Design

The system follows a simple layered structure, separating data, logic, I/O,
and user interaction into four classes plus one custom exception:

- **Product** — the data layer. Stores one product's attributes and enforces
  validation on them directly (encapsulation).
- **Inventory** — the logic layer. Owns the `ArrayList<Product>` and
  implements every operation (add, search, update, delete, stock changes,
  low-stock check, total value). Does not know about the console or files.
- **FileManager** — the persistence layer. Converts the product list to and
  from lines of text in `data/products.txt`. Does not know about validation
  rules or the menu.
- **Main** — the presentation layer. Displays the menu, reads console input
  with `Scanner`, converts raw text into calls on `Inventory`/`FileManager`,
  and catches exceptions to show friendly error messages.
- **DuplicateProductException** — a custom checked exception used specifically
  when `Inventory.addProduct()` detects a duplicate ID.

This layering means each class can be understood, tested, and explained in
isolation, and a change to one layer (e.g. switching file storage for a
database) would not require rewriting the others.

## Class Descriptions

**Product**: Holds `id`, `name`, `category`, `price`, `quantity`, and
`minStockLevel`. The constructor and all setters validate their inputs
(non-empty name/ID, non-negative price/quantity/minStockLevel), so an
invalid `Product` object can never exist in memory. Provides
`getStockValue()`, `isLowStock()`, and conversion methods `toFileLine()` /
`fromFileLine()` for persistence.

**Inventory**: Holds the `List<Product>` and implements every business
operation described in the functional requirements. Uses a linear search
(`findById`) since the expected data size for a course project is small;
this keeps the logic easy to read and explain without needing a `HashMap`.

**FileManager**: Wraps `FileWriter`/`BufferedReader` to save and load the
product list as plain comma-separated text, one product per line. If the
file does not exist yet, `load()` returns an empty list instead of throwing
an error, so the very first run of the program works without any setup.

**Main**: Contains the `main()` method, the menu-printing logic, and one
private helper method per menu option. Uses a `while` loop with a `switch`
statement to keep dispatching simple and readable. Wraps risky calls in
`try/catch` blocks to turn exceptions into user-friendly messages.

**DuplicateProductException**: A minimal custom `Exception` subclass with
just a constructor that passes its message to the parent class. Demonstrates
that a program can define exceptions specific to its own business rules,
not just rely on built-in ones.

## Algorithm / Working

1. On startup, `Main` creates an empty `Inventory` and attempts to load any
   existing data via `FileManager.load()`.
2. The program enters a loop: print the menu, read the user's choice as a
   string, and use a `switch` statement to call the matching private method.
3. Each menu method reads any further input it needs (e.g. product ID, new
   quantity) and delegates the actual work to a method on `Inventory`.
4. `Inventory` methods perform the requested operation on the internal
   `ArrayList<Product>`, throwing an exception if the request is invalid
   (duplicate ID, product not found, insufficient stock, negative numbers).
5. `Main` catches any thrown exception at the top level of the loop and
   prints its message, then loops back to show the menu again — so one bad
   input never crashes the whole program.
6. Saving/loading (`FileManager`) is only triggered when the user explicitly
   chooses options 10 or 11, so the user has full control over when disk
   I/O happens.

## OOP Concepts Used

- **Encapsulation**: `Product`'s fields are private, and every mutation goes
  through a validating setter or the constructor.
- **Classes and objects**: Each `Product` is an independent object with its
  own state; `Inventory` is a class managing a collection of them.
- **Constructors**: `Product`'s constructor enforces all validation rules
  at creation time.
- **Methods**: Each class exposes a small, purpose-specific set of public
  methods (e.g. `addStock`, `removeStock`, `getTotalInventoryValue`).
- **Collections**: `ArrayList<Product>` stores the inventory in `Inventory`.
- **Exception handling**: Built-in exceptions (`IllegalArgumentException`,
  `NumberFormatException`, `IOException`) and one custom exception
  (`DuplicateProductException`) are used and caught at appropriate layers.
- **File handling**: `FileWriter` and `BufferedReader`/`FileReader` handle
  saving and loading data in `FileManager`.

## File Handling

Data is stored as plain comma-separated text in `data/products.txt`, one
product per line, in the format:

```
id,name,category,price,quantity,minStockLevel
```

`FileManager.save()` overwrites the file with the current in-memory product
list. `FileManager.load()` reads the file line by line, skipping and
warning about any line that fails to parse (rather than crashing), so a
manually corrupted file degrades gracefully instead of stopping the program.

## Testing

See `TESTING.md` for the full table of test cases. All 17 listed tests were
actually executed against the compiled program (not merely predicted), using
piped console input to simulate a user typing at each menu prompt, and every
one passed. No results in that document were fabricated or assumed.

## Sample Outputs

Below is real terminal output, taken from an actual run of the compiled
program:

```
Enter your choice: 1
Product ID: P1
Product name: Rice Bag 5kg
Category: Grocery
Price: 50
Quantity: 100
Minimum stock level: 20
Product added successfully.

Enter your choice: 9
Total inventory value: $5000.00
```

## Limitations

- Plain-text storage has no encryption and only basic protection against
  corrupted data (corrupted lines are skipped, not repaired).
- No concurrency or multi-user support — designed for a single user in a
  single terminal session.
- No sorting/filtering beyond search and the low-stock view.
- Linear search (`O(n)`) is used for lookups, which is appropriate for a
  small course-project dataset but would not scale to a very large catalog.

## Future Scope

- Replace linear search with a `HashMap<String, Product>` keyed by ID for
  faster lookups at larger scale.
- Add CSV import/export for interoperability with spreadsheet tools.
- Introduce a transaction/audit log recording every stock change with a timestamp.
- Migrate storage to a lightweight embedded database (e.g. SQLite via JDBC)
  as a follow-up learning exercise.
- Add a JUnit test suite to automate the manual tests currently documented
  in `TESTING.md`.

## Conclusion

This project demonstrates a complete, working command-line inventory system
built from fundamental Java concepts: encapsulated classes, a collection-based
data layer, validated input, custom and built-in exception handling, and
file-based persistence. Each class has a single, clear responsibility, which
keeps the code readable and defensible in an academic setting while still
solving a genuine small-scale inventory-tracking problem.

## References

- Oracle, "The Java Tutorials" — https://docs.oracle.com/javase/tutorial/
- Oracle, "Java SE Documentation" — https://docs.oracle.com/en/java/javase/
  (used for standard reference on `ArrayList`, `Scanner`, `FileWriter`,
  `BufferedReader`, and exception handling syntax)
