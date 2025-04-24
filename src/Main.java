import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;

public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao(DB.getConnection());
        SellerDao sellerDao = DaoFactory.createSellerDao(DB.getConnection());

        DB.closeConnection();
    }
}
