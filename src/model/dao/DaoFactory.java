package model.dao;

import db.DB;
import model.impl.DepartmentJDBC;

import java.sql.Connection;

public class DaoFactory {
    public static DepartmentDao createDepartmentDao(Connection conn) {
        return new DepartmentJDBC(conn);
    }
}
