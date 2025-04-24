package model.dao;

import db.DB;
import model.impl.DepartmentJDBC;
import model.impl.SellerJDBC;

import java.sql.Connection;

public class DaoFactory {
    public static DepartmentDao createDepartmentDao(Connection conn) {
        return new DepartmentJDBC(conn);
    }

    public static SellerDao createSellerDao(Connection conn) {
        return new SellerJDBC(conn);
    }
}
