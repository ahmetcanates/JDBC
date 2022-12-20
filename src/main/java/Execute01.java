import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adim : Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2.Adim : Database'e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","54140370318a");
        //3.Adim : Statement olustur
        Statement st = con.createStatement();

        /*
            execute() methodu DDL(create, drop, alter table) ve DQL(SELECT) icin kullanilabilir.
            1)Eger execute() methodu DDL icin kullanilirsa 'false' return eder
            2)Eger execute() methodu DQL icin kullanilirsa ResultSet alindiginda 'true' aksi halde 'false' verir.
         */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.

        boolean sql1 = st.execute("CREATE TABLE workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        System.out.println("sql1 = " + sql1);//false return eder cunku data cagirmiyoruz.

        //2.Ornek: Table'a worker_address sutunu ekleyerek alter yapin.
        String sql2 = "ALTER TABLE workers ADD worker_address varchar(80)";
        boolean sql2b = st.execute(sql2);
        System.out.println("sql2b = " + sql2b);

        //3.Ornek: workers table'ini silin
        String sql3 = "DROP TABLE workers;";
        boolean sql3b = st.execute(sql3);
        System.out.println("sql3b = " + sql3b);


        //5.Adim: Baglanti ve statementi kapat
        con.close();
        st.close();
    }
}
