import com.neo.beans.CompleteTest;
import com.neo.beans.Student;
import com.neo.dao.connection.WrappedConnection;
import com.neo.dao.daofactory.AbstractDAOFactory;
import com.neo.dao.idao.ICompleteTestsDAO;
import com.neo.dao.idao.IStudentsDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class __CompletedTestsTest {
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

            ICompleteTestsDAO iCompleteTestsDAO = AbstractDAOFactory.getDAOFactory().getCompleteTestsDAO(wrappedConnection);

            List<CompleteTest> completeTests = iCompleteTestsDAO.findAll();
            System.out.println(completeTests.size());

            System.out.println(completeTests);

//            CompleteTest completeTest = completeTests.get(0);
//
//            System.out.println("Deleted test with id : " + completeTests.get(0).getStudentId());
//            iCompleteTestsDAO.deleteById(completeTests.get(0).getId());
//            completeTests = iCompleteTestsDAO.findAll();
//            System.out.println(completeTests);
            int lastTestStudentId = completeTests.get(completeTests.size()-1).getStudentId();
            System.out.println("Test by student with id = " + lastTestStudentId);
            completeTests = iCompleteTestsDAO.findAllCompletedTestsByStudent(lastTestStudentId);
            System.out.println(completeTests);



        } catch (Exception e) {
            e.printStackTrace();
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
