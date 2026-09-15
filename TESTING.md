# Test Cases and Results

All tests below were actually executed against the compiled program
(`javac -d bin src/*.java` followed by `java -cp bin Main`), using piped
input to simulate a user typing at the menu. None of these results are
assumed — each was observed in the terminal output during development.

| # | Test Case | Input Summary | Expected Result | Actual Result |
|---|-----------|----------------|------------------|----------------|
| 1 | Add a valid product | ID=P1, Rice Bag 5kg, Grocery, price=50, qty=100, min=20 | Product added, appears in Display All | Added successfully; displayed correctly in table | PASS |
| 2 | Duplicate ID | Add P1 again with different details | Rejected with a clear error, original P1 untouched | `Error: A product with ID 'P1' already exists.` — original data unchanged | PASS |
| 3 | Invalid price | Enter "abc" for price, then a valid number | Program re-prompts instead of crashing | `That's not a valid number. Try again.` then accepted valid input | PASS |
| 4 | Invalid quantity | Enter "xyz" for quantity, then a valid number | Program re-prompts instead of crashing | `That's not a valid whole number. Try again.` then accepted valid input | PASS |
| 5 | Search existing product (by ID) | Search "P1" | Returns P1's details | P1 row displayed | PASS |
| 6 | Search existing product (by partial name) | Search "Rice" | Returns P1 (name contains "Rice") | P1 row displayed | PASS |
| 7 | Search missing product | Search "Ghost" | Clear "not found" message | `No matching product found.` | PASS |
| 8 | Update a product | Change P1's name to "Basmati Rice 5kg" and price to 60, leave category/min stock blank | Name and price change, other fields unchanged | Name and price updated; category ("Grocery") and min stock (20) unchanged | PASS |
| 9 | Delete an existing product | Delete P1 | Product removed, no longer appears in Display All | `Product deleted.` — Display All showed nothing afterward | PASS |
| 10 | Delete a missing product | Delete "Ghost" | Clear "not found" message, no crash | `No product found with ID 'Ghost'.` | PASS |
| 11 | Add stock | P1 qty 100, add 50 | Quantity becomes 150 | Quantity confirmed as 150 (then further reduced in test 12) | PASS |
| 12 | Sell/remove stock (valid) | P1 qty 150, remove 30 | Quantity becomes 120 | Quantity confirmed as 120 | PASS |
| 13 | Insufficient stock | P1 qty 120, attempt to remove 9999 | Rejected, quantity unchanged | `Error: Cannot remove 9999 units - only 120 in stock.` — quantity stayed at 120 | PASS |
| 14 | Low-stock detection | P1 qty=15 min=20 (below minimum); P2 qty=100 min=10 (above minimum) | Only P1 appears in the low-stock list | Only P1 shown, marked `LOW STOCK`; P2 correctly excluded | PASS |
| 15 | Total inventory value | P1: price 50 x qty 100 = 5000; P2: price 10 x qty 50 = 500 | Total = 5500.00 | `Total inventory value: $5500.00` | PASS |
| 16 | Save data | Save P1 to file | `data/products.txt` created/updated with P1's data | File created; content matched `P1,Rice Bag 5kg,Grocery,50.0,100,20` | PASS |
| 17 | Load data (separate run) | Start a brand-new run of the program after test 16 | Program auto-loads P1 on startup without re-entering it | `Loaded 1 product(s) from data/products.txt`; P1 appeared in Display All | PASS |

## How to re-run these tests yourself

You can reproduce any of the above manually:

1. Compile and run the program as described in the README.
2. At the menu, type the option number and press Enter, then answer each
   prompt on its own line and press Enter.
3. Compare the program's response to the "Expected Result" column above.

For a fully automated check, you can pipe a sequence of menu choices and
answers into the program from a terminal, for example:

```
printf "1\nP1\nRice Bag 5kg\nGrocery\n50\n100\n20\n2\n12\n" | java -cp bin Main
```

This types option `1` (add product), fills in each prompt, then `2`
(display all), then `12` (exit) — automatically, without you needing to type
each line by hand.
