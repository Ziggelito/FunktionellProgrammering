import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Main {
    public static void props(String[] args) throws FileNotFoundException, IOException, SQLException {
        Repository r = new Repository();
         List<Customers> customersList = r.getCustomers();
        customersList.forEach(c -> System.out.println());
    }
}