# GitHub Preparation Checklist

Go through this yourself before pushing — nothing here has been done for you,
and no repository or URL has been created on your behalf.

- [ ] Create a new, empty repository on GitHub (do not import a template).
- [ ] Confirm the repository name matches your project (e.g. `inventory-management-system`).
- [ ] Copy this entire `InventoryManagementSystem/` folder into your local Git repo folder.
- [ ] Confirm `bin/` (compiled `.class` files) is excluded — check that `.gitignore` is present and working (`git status` should not show `bin/`).
- [ ] Run `git init`, `git add .`, `git commit -m "Initial commit: inventory management system"`.
- [ ] Set the remote: `git remote add origin <your-repo-URL>` (use the URL GitHub gives you after creating the repo — do not guess or reuse a URL from anywhere else).
- [ ] Push: `git branch -M main` then `git push -u origin main`.
- [ ] Verify on GitHub's website that `README.md` renders correctly on the repo's main page.
- [ ] Verify `data/products.txt` is either absent (fresh clone) or contains only sample/test data you're comfortable sharing — delete it before committing if you want a clean first run for evaluators.
- [ ] Double-check the README's compile/run commands actually work on a freshly cloned copy of the repo, not just your working folder.
- [ ] If your course requires it, add a LICENSE file (check with your instructor which license, if any, is expected).
- [ ] Re-read through `Main.java`, `Product.java`, `Inventory.java`, and `FileManager.java` yourself and make sure you can explain every method — you're responsible for defending this code.
