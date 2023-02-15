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
        p.load(new FileInputStream("src/Properties"));

        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select CustomerID, CustomerName, CustomerPass from Customers"))

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

    List<Categories> getCategories() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select CategoryID, Category from Categories"))

        {
            List<Categories> categories = new ArrayList<>();

            while (rs.next()){
                Categories temp = new Categories();
                int id = rs.getInt("CategoryID");
                temp.setCategoryID(id);
                String cat = rs.getString("Category");
                temp.setCategory(cat);
                categories.add(temp);


            }

            return categories;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    List<BelongsTo> getBelongsTo() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select ProductID, CategoryID from BelongsTo"))

        {
            List<BelongsTo> belongsTo = new ArrayList<>();

            while (rs.next()){
                BelongsTo temp = new BelongsTo();
                int id = rs.getInt("ProductID");
                temp.setProductID(id);
                int catId = rs.getInt("CategoryID");
                temp.setCategoryID(catId);
                belongsTo.add(temp);


            }

            return belongsTo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
