import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao(DB.getConnection());

        DB.closeConnection();
    }
}
