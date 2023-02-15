import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        Main main = new Main();
        main.Program();

    }
    public void StoredStoreProcedure(Customers c, Orders o, Products pro) throws SQLException, IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"))) {

            CallableStatement cstmt = con.prepareCall("CALL AddToCart(?,?,?)");
            cstmt.setInt(1, c.getCustomerID());
            cstmt.setInt(2, 1);
            cstmt.setInt(3, 1);
            cstmt.executeQuery();


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
                        for (int i = 0; i < customersList.size(); i++) {
                            if (name.equalsIgnoreCase(customersList.get(i).getCustomerName())) {
                                System.out.println(customersList.get(i).getCustomerPass());
                                System.out.println("Skriv in lösenord:");
                                int password = Integer.parseInt(scan.nextLine());
                                found = true;
                                if (password == (customersList.get(i).getCustomerPass())) {
                                    System.out.println("Funkar");
                                    login(customersList.get(i));
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
                //case (1) ->  beställningen;
                case (2) -> startLoop = false;
                default -> System.out.println("Felaktigt nummer");
            }
        }
        while(startLoop);
    }

    }
