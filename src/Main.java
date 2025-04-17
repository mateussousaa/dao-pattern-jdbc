import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = DB.getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM department");

            while(rs.next()) {
                System.out.println(rs.getInt("Id") + " " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DB.closeConnection();
        DB.closeResultSet(rs);
        DB.closeStatement(st);
    }
}
