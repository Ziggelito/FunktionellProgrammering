import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


public class Main  {


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.Program();

    }
    public void StoredStoreProcedure(Customers customer,int ord, int pro) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));
        Repository r = new Repository();
        List<Products> productsList = r.getProducts();

        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"))) {

            CallableStatement stmt = con.prepareCall("CALL AddToCart(?,?,?)");
            stmt.setInt(1, customer.getCustomerID());
            stmt.setInt(2, ord);
            stmt.setInt(3, pro);
            stmt.executeQuery();
            System.out.println("Du har beställt produkt: Storlek: " +  productsList.get(pro).getSize() + " Färg: " + productsList.get(pro).getColor() +  " Pris: " + productsList.get(pro).getPrice() + "Kr Märke: " + productsList.get(pro).getBrand() + " Lager: " + productsList.get(pro).getStock() + "! Hoppas du blir nöjd med ditt köp." );


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







        int answer = inputInt ("Vilken sko vill du beställa?:" +
                "\n1. Storlek: " + productsList.get(0).getSize() + " Färg: " + productsList.get(0).getColor() +  " Pris: " + productsList.get(0).getPrice() + "Kr " + productsList.get(0).getBrand() + " Lager: " + productsList.get(0).getStock() +
                "\n2. Storlek: " + productsList.get(1).getSize() + " Färg: " + productsList.get(1).getColor() +  " Pris: " + productsList.get(1).getPrice() + "Kr " + productsList.get(1).getBrand() + " Lager: " + productsList.get(1).getStock() +
                "\n3. Storlek: " + productsList.get(2).getSize() + " Färg: " + productsList.get(2).getColor() +  " Pris: " + productsList.get(2).getPrice() + "Kr " + productsList.get(2).getBrand() + " Lager: " + productsList.get(2).getStock() +
                "\n3. Storlek: " + productsList.get(3).getSize() + " Färg: " + productsList.get(3).getColor() +  " Pris: " + productsList.get(3).getPrice() + "Kr " + productsList.get(3).getBrand() + " Lager: " + productsList.get(3).getStock() +
                "\n4. Storlek: " + productsList.get(4).getSize() + " Färg: " + productsList.get(4).getColor() +  " Pris: " + productsList.get(4).getPrice() + "Kr " + productsList.get(4).getBrand() + " Lager: " + productsList.get(4).getStock() +
                "\n5. Storlek: " + productsList.get(4).getSize() + " Färg: " + productsList.get(4).getColor() +  " Pris: " + productsList.get(4).getPrice() + "Kr " + productsList.get(4).getBrand() + " Lager: " + productsList.get(4).getStock() +
                "\n6. Storlek: " + productsList.get(5).getSize() + " Färg: " + productsList.get(5).getColor() +  " Pris: " + productsList.get(5).getPrice() + "Kr " + productsList.get(5).getBrand() + " Lager: " + productsList.get(5).getStock() +
                "\n7. Storlek: " + productsList.get(6).getSize() + " Färg: " + productsList.get(6).getColor() +  " Pris: " + productsList.get(6).getPrice() + "Kr " + productsList.get(6).getBrand() + " Lager: " + productsList.get(6).getStock() +
                "\n8. Storlek: " + productsList.get(7).getSize() + " Färg: " + productsList.get(7).getColor() +  " Pris: " + productsList.get(7).getPrice() + "Kr " + productsList.get(7).getBrand() + " Lager: " + productsList.get(7).getStock()  );
        productsList.stream().sorted(Comparator.comparingInt(Products::getProductID)).forEach(s -> System.out.println(s.getBrand()+"\t"
                +s.getColor()+" \t"+ s.getSize()+"\t\t\t"+s.getPrice()+" \t"+s.getStock()));


        switch (answer){

            case (1), (2), (3), (4), (5), (6), (7), (8) -> StoredStoreProcedure(customer, ordersList.size()+1,answer);
            default -> System.out.println("Felaktigt nummer");
        }

    }



    }
