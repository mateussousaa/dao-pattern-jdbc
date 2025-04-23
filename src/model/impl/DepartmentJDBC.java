package model.impl;

import db.DB;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, department.getName());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error to insert a Department");
            }

            rs = st.getGeneratedKeys();

            while (rs.next()) {
                int id = rs.getInt(1);
                department.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? " +
                            "WHERE Id = ?"
            );
            st.setString(1, department.getName());
            st.setInt(2, department.getId());

            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No department found with the given id for update.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1, id);

            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No department found with the given id for deletion.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?;");
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                return new Department(rs.getInt("Id"), rs.getString("Name"));
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Department> findAll() {
        Statement st = null;
        ResultSet rs = null;

        List<Department> departments = new ArrayList<Department>();

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM department");

            while (rs.next()) {
                Department current = new Department(rs.getInt("Id"), rs.getString("Name"));
                departments.add(current);
            }

            return departments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
