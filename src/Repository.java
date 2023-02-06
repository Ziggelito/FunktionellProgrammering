import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
    List<Customers> getCustomers() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("Properties"));

        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select CustomerID, CustomerName, CustomerPass from Customers "))

        {
            List<Customers> customers = new ArrayList<>();

            while (rs.next()){
                Customers temp = new Customers();
                int id = rs.getInt("CustomerID");
                temp.setCustomerID(id);
                String name = rs.getString("CustomerName");
                temp.setCustomerName(name);
                int pass = rs.getInt("CustomerPass");
                temp.setCustomerPass(pass);
                customers.add(temp);


            }

            return customers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
