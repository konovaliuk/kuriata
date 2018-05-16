import com.neo.beans.Admin;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.daofactory.AbstractDAOFactory;
import com.neo.dao.idao.IAdminsDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

public class __Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/fast_test";
        String user = "root";
        String pass = "root";
        Properties prop = new Properties();
        prop.put("user", user);
        prop.put("password", pass);
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(url, prop);
            WrappedConnection wrappedConnection = new WrappedConnection(connection);
            IAdminsDAO iAdminsDAO = AbstractDAOFactory.getDAOFactory().getAdminsDAO(wrappedConnection);
            List<Admin> adminsList = iAdminsDAO.findAll();
            System.out.println(adminsList);
            Admin admin = adminsList.get(2);
            System.out.println("Deleted: " + admin);
            iAdminsDAO.deleteById(admin.getId());
            adminsList = iAdminsDAO.findAll();
            System.out.println(adminsList);
            admin = adminsList.get(0);
            admin.setName("ololo");
            admin.setPassword("ololo");
            System.out.println("Admin 0 changed to: " + admin);
            iAdminsDAO.update(admin);
            System.out.println(iAdminsDAO.findAll());
        } catch (Exception e) {
            System.err.println("properties file is missing " + e);
        }
    }
}
