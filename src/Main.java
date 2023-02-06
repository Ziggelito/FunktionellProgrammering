import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public  void connectToAndQueryDatabase (String username, String password) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost3306/shoes?serverTimezone=UTC&useSSl=false&allowPublicRetrieval=true", username, password);)
        {

        }
    }
}