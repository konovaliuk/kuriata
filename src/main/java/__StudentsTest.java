import com.neo.beans.Admin;
import com.neo.beans.Student;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.daofactory.AbstractDAOFactory;
import com.neo.dao.idao.IAdminsDAO;
import com.neo.dao.idao.IStudentsDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class __StudentsTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/fast_test";
        String user = "root";
        String pass = "root";
        Properties prop = new Properties();
        prop.put("user", user);
        prop.put("password", pass);

        WrappedConnection wrappedConnection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(url, prop);
            wrappedConnection = new WrappedConnection(connection);

            IStudentsDAO iStudentsDAO = AbstractDAOFactory.getDAOFactory().getStudentsDAO(wrappedConnection);

            List<Student> studentsList = iStudentsDAO.findAll();
            System.out.println(studentsList);
            Student student = studentsList.get(2);
            System.out.println("Deleted: " + student);
            iStudentsDAO.deleteById(student.getId());
            studentsList = iStudentsDAO.findAll();
            System.out.println(studentsList);
            student = studentsList.get(0);
            student.setFirstName("FirstName");
            student.setLastName("LastName");
            student.setEmail("Email");
            System.out.println("Student 0 changed to: " + student);
            iStudentsDAO.update(student);
            System.out.println(iStudentsDAO.findAll());
        } catch (Exception e) {
            System.err.println("properties file is missing " + e);
        } finally {
            try {
                wrappedConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
