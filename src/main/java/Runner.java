import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args){
        //1.Adim: Driver'a kaydol
        //2.Adim: Database'e baglan
        Connection connection = JdbcUtils.connectToDatabase("localhost","techproed","postgres","54140370318a");

        //3.Adim: Statement olustur.
        Statement statement = JdbcUtils.createStatement();

        //4.Adim: Query calistir.
        //JdbcUtils.execute("CREATE TABLE students (name varchar(20), id int, address varchar(80))");

        //JdbcUtils.createTable("def","classes VARCHAR(20)","teacherName VARCHAR(20)","id int");

        System.out.println(JdbcUtils.getColumnList("classes", "abc"));
        //5.Adim: Baglanti ve statement'i kapat.
        JdbcUtils.closeConnectionAndStatement();


    }
}
