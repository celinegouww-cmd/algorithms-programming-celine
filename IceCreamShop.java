import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IceCreamShop {

    // ---- Ice cream menu item ----
    static class IceCream {
        String name;
        double basePrice; // price for MEDIUM size

        IceCream(String name, double basePrice) {
            this.name = name;
            this.basePrice = basePrice;
        }
    }

    // Size multipliers applied to the base (medium) price
    static double sizeMultiplier(char size) {
        switch (size) {
            case 'S': return 0.80;  // Small  = 20% cheaper than medium
            case 'M': return 1.00;  // Medium = base price
            case 'L': return 1.35;  // Large  = 35% more than medium
            default:  return 1.00;
        }
    }

    static String sizeName(char size) {
        switch (size) {
            case 'S': return "Small";
            case 'M': return "Medium";
            case 'L': return "Large";
            default:  return "Medium";
        }
    }

    public static void main(String[] args) {
        // ---- Menu: 15 flavors priced between $6 and $100 (medium base price) ----
        List<IceCream> menu = new ArrayList<>();
        menu.add(new IceCream("Vanilla Classic",            6.00));
        menu.add(new IceCream("Chocolate Fudge",             7.50));
        menu.add(new IceCream("Strawberry Swirl",            7.50));
        menu.add(new IceCream("Mint Chocolate Chip",         8.00));
        menu.add(new IceCream("Cookies and Cream",           8.50));
        menu.add(new IceCream("Butter Pecan",                9.00));
        menu.add(new IceCream("Salted Caramel",              9.50));
        menu.add(new IceCream("Mango Sorbet",                10.00));
        menu.add(new IceCream("Pistachio Delight",           12.00));
        menu.add(new IceCream("Rocky Road",                  12.50));
        menu.add(new IceCream("Matcha Green Tea",             15.00));
        menu.add(new IceCream("Belgian Chocolate Truffle",   25.00));
        menu.add(new IceCream("Black Truffle Gelato",        45.00));
        menu.add(new IceCream("Gold Leaf Vanilla Bean",      75.00));
        menu.add(new IceCream("Diamond Sundae Deluxe",       100.00));

        Scanner sc = new Scanner(System.in);
        List<String> receiptLines = new ArrayList<>();
        double total = 0.0;
        boolean ordering = true;

        System.out.println("========================================");
        System.out.println("        WELCOME TO THE ICE CREAM SHOP");
        System.out.println("========================================");

        while (ordering) {
            printMenu(menu);

            System.out.print("\nEnter item number to order (0 to checkout): ");
            int choice = readInt(sc);

            if (choice == 0) {
                ordering = false;
                break;
            }

            if (choice < 1 || choice > menu.size()) {
                System.out.println(">> Invalid item number. Please try again.\n");
                continue;
            }

            IceCream selected = menu.get(choice - 1);

            char size = readSize(sc);

            System.out.print("Quantity: ");
            int qty = readInt(sc);
            if (qty < 1) qty = 1;

            double unitPrice = round2(selected.basePrice * sizeMultiplier(size));
            double lineTotal = round2(unitPrice * qty);
            total += lineTotal;

            String line = String.format("%-28s [%-6s] x%d  @ $%-7.2f = $%.2f",
                    selected.name, sizeName(size), qty, unitPrice, lineTotal);
            receiptLines.add(line);

            System.out.println(">> Added: " + line + "\n");
        }

        printReceipt(receiptLines, total);
        sc.close();
    }

    static void printMenu(List<IceCream> menu) {
        System.out.println("\n---------------- MENU ----------------");
        System.out.printf("%-4s %-28s %-10s%n", "No.", "Flavor", "Price(M)");
        System.out.println("----------------------------------------");
        for (int i = 0; i < menu.size(); i++) {
            IceCream ic = menu.get(i);
            System.out.printf("%-4d %-28s $%-9.2f%n", i + 1, ic.name, ic.basePrice);
        }
        System.out.println("----------------------------------------");
        System.out.println("Sizes: S (small, -20%) | M (medium) | L (large, +35%)");
    }

    static char readSize(Scanner sc) {
        while (true) {
            System.out.print("Size (S/M/L): ");
            String s = sc.next().trim().toUpperCase();
            if (s.equals("S") || s.equals("M") || s.equals("L")) {
                return s.charAt(0);
            }
            System.out.println(">> Invalid size, please enter S, M, or L.");
        }
    }

    static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print(">> Please enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    static void printReceipt(List<String> lines, double total) {
        System.out.println("\n========================================");
        System.out.println("              YOUR RECEIPT");
        System.out.println("========================================");
        if (lines.isEmpty()) {
            System.out.println("No items ordered.");
        } else {
            for (String l : lines) {
                System.out.println(l);
            }
        }
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL: $%.2f%n", total);
        System.out.println("========================================");
        System.out.println("Thank you for visiting the Ice Cream Shop!");
    }
}