import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class Main  {


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.Program();

    }
    public void StoredStoreProcedure() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"))) {

            CallableStatement stmt = con.prepareCall("CALL AddToCart(?,?,?)");
            stmt.setInt(1, 1);
            stmt.setInt(2, 7);
            stmt.setInt(3, 1);
            stmt.executeQuery();


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int inputInt(String text) {
        while (true) {
            Scanner scan;
            System.out.println(text);
            try {
                scan = new Scanner(System.in);
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Förväntade mig ett nummer");
            } catch (NumberFormatException e) {
                System.out.println("Inte nummer");
            }
        }
    }
     void Program() throws IOException {
        Repository r = new Repository();
        List<Customers> customersList = r.getCustomers();
        System.out.println("\nVälkommen till skobutiken");

            while (true) {

                int answer = inputInt("""
                    Vad vill du göra?
                    1. Logga in
                  
                    2. Avsluta Programmet""");
                switch (answer) {
                    case (1) -> {
                        System.out.println("\nSkriv in Användarnamn:");
                        Scanner scan = new Scanner(System.in);
                        String name = scan.nextLine();
                        boolean found = false;
                        for (Customers customers : customersList) {
                            if (name.equalsIgnoreCase(customers.getCustomerName())) {
                                System.out.println(customers.getCustomerPass());
                                System.out.println("Skriv in lösenord:");
                                int password = Integer.parseInt(scan.nextLine());
                                found = true;
                                if (password == (customers.getCustomerPass())) {
                                    System.out.println("Funkar");
                                    login(customers);
                                } else {
                                    System.out.println("Felaktigt lösenord.\n");
                                }
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Användaren hittades inte\n");
                        }
                    }
                    case (2) -> System.exit(0);
                    default -> System.out.println("Felaktigt nummer");
                }
            }
    }

    private void login (Customers customer) throws IOException {

        boolean startLoop = true;
        do {
            int answer = inputInt("Välkommen " + customer.getCustomerName() +
                    "\n1. Göra en beställning\n2. Logga ut");
            switch (answer) {
                case (1) -> Ordering(customer);
                case (2) -> startLoop = false;
                default -> System.out.println("Felaktigt nummer");
            }
        }
        while(startLoop);
    }
    private void Ordering (Customers customer) throws IOException  {
        Repository r = new Repository();
        List<Products> productsList = r.getProducts();
        List<Orders> ordersList = r.getOrders();





        int answer = inputInt("Vilken sko vill du beställa?:" +
                "\n1." + productsList.get(0).getSize() + " " + productsList.get(0).getColor() +  " " + productsList.get(0).getPrice() + "Kr " + productsList.get(0).getBrand() + " " + productsList.get(0).getStock() +
                "\n2." + productsList.get(1).getSize() + " " + productsList.get(1).getColor() +  " " + productsList.get(1).getPrice() + "Kr " + productsList.get(1).getBrand() + " " + productsList.get(1).getStock() +
                "\n3." + productsList.get(2).getSize() + " " + productsList.get(2).getColor() +  " " + productsList.get(2).getPrice() + "Kr " + productsList.get(2).getBrand() + " " + productsList.get(2).getStock() +
                "\n3." + productsList.get(3).getSize() + " " + productsList.get(3).getColor() +  " " + productsList.get(3).getPrice() + "Kr " + productsList.get(3).getBrand() + " " + productsList.get(3).getStock() +
                "\n4." + productsList.get(4).getSize() + " " + productsList.get(4).getColor() +  " " + productsList.get(4).getPrice() + "Kr " + productsList.get(4).getBrand() + " " + productsList.get(4).getStock() +
                "\n5." + productsList.get(4).getSize() + " " + productsList.get(4).getColor() +  " " + productsList.get(4).getPrice() + "Kr " + productsList.get(4).getBrand() + " " + productsList.get(4).getStock() +
                "\n6." + productsList.get(5).getSize() + " " + productsList.get(5).getColor() +  " " + productsList.get(5).getPrice() + "Kr " + productsList.get(5).getBrand() + " " + productsList.get(5).getStock() +
                "\n7." + productsList.get(6).getSize() + " " + productsList.get(6).getColor() +  " " + productsList.get(6).getPrice() + "Kr " + productsList.get(6).getBrand() + " " + productsList.get(6).getStock() +
                "\n8." + productsList.get(7).getSize() + " " + productsList.get(7).getColor() +  " " + productsList.get(7).getPrice() + "Kr " + productsList.get(7).getBrand() + " " + productsList.get(7).getStock()  );


        switch (answer){

            case (1) -> StoredStoreProcedure();
            case (2) -> System.out.println( "Grattis" + customer.getCustomerName() + "Du har beställt nummer 2");
            case (3) -> System.out.println("Du har beställt nummer 3");
            case (4) -> System.out.println("Du har beställt nummer 4");
            case (5) -> System.out.println("Du har beställt nummer 5");
            case (6) -> System.out.println("Du har beställt nummer 6");
            case (7) -> System.out.println("Du har beställt nummer 7");
            case (8) -> System.out.println("Du har beställt nummer 8");
            default -> System.out.println("Felaktigt nummer");
        }

    }



    }
