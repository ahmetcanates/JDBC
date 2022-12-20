import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcPractice {
    @Test
            public void medunnaMessageEmailTest () throws SQLException {
        //1-MedunnaMessageEmailTest
        //User connects to the database
        //        JdbcUtils.connectToDataBase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        //        Statement statement = JdbcUtils.createStatement();
        //User sends the query to get the names of "email" column from "cmessage" table
        //Assert that there are some "cmessage" email "zeynep05@gmail.com".
        //User closes the connection

        JdbcUtils.connectToDatabase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();

        String sql1 = "SELECT email FROM cmessage";
        ResultSet rs1 = JdbcUtils.executeQry(sql1);
        List<String> emails = new ArrayList<>();

        while(rs1.next()) {
            emails.add(rs1.getString(1));
        }
        System.out.println("emails = " + emails);

        Assert.assertTrue(emails.contains("zeynep05@gmail.com"));
        statement.close();
        System.out.println(statement.isClosed());

    }

}
