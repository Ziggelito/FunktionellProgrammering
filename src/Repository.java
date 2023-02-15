import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Repository {
    List<Customers> getCustomers() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select CustomerID, CustomerName, CustomerPass from Customers")) {
            List<Customers> customers = new ArrayList<>();

            while (rs.next()) {
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

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select CategoryID, Category from Categories")) {
            List<Categories> categories = new ArrayList<>();

            while (rs.next()) {
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

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select ProductID, CategoryID from BelongsTo")) {
            List<BelongsTo> belongsTo = new ArrayList<>();

            while (rs.next()) {
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

    List<Includes> getIncludes() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select OrderID, ProductID from Includes")) {
            List<Includes> includes = new ArrayList<>();

            while (rs.next()) {
                Includes temp = new Includes();
                int id = rs.getInt("OrderID");
                temp.setOrderID(id);
                int catId = rs.getInt("ProductID");
                temp.setProductID(catId);
                includes.add(temp);


            }

            return includes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    List<Orders> getOrders() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select OrderID, OrderDate, Location FROM Orders")) {
            List<Orders> orders = new ArrayList<>();

            while (rs.next()) {
                Orders temp = new Orders();
                int id = rs.getInt("OrderID");
                temp.setOrderID(id);
                Date oDate = rs.getDate("OrderDate");
                temp.setOrderDate(oDate);
                String location = rs.getString("Location");
                temp.setLocation(location);
                orders.add(temp);


            }

            return orders;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    List<PlaceOrder> getPlaceOrder() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select OrderID, ProductID from Includes")) {
            List<PlaceOrder> placeOrder = new ArrayList<>();

            while (rs.next()) {
                PlaceOrder temp = new PlaceOrder();
                int id = rs.getInt("OrderID");
                temp.setOrderID(id);
                int cId = rs.getInt("CustomerID");
                temp.setCustomerID(cId);
                placeOrder.add(temp);


            }

            return placeOrder;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    List<Products> getProducts() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/Properties"));

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select ProductID, Size, Color, Price, Brand, Stock FROM Products")) {
            List<Products> products = new ArrayList<>();

            while (rs.next()) {
                Products temp = new Products();
                int id = rs.getInt("ProductID");
                temp.setProductID(id);
                int size = rs.getInt("Size");
                temp.setSize(size);
                String col = rs.getString("Color");
                temp.setColor(col);
                int pri = rs.getInt("Price");
                temp.setPrice(pri);
                String bra = rs.getString("Brand");
                temp.setBrand(bra);
                int sto = rs.getInt("Stock");
                temp.setStock(sto);

                products.add(temp);


            }

            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
