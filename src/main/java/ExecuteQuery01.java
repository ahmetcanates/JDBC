import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","54140370318a");

        Statement st = con.createStatement();

        //1.Ornek : region id'si 1 olan "country name" degerlerini cagirin

        String sql1 = "SELECT country_name from countries where region_id=1";
        boolean r1 = st.execute(sql1);
        System.out.println("r1 = " + r1);

        //Recordlari(Row - Satir yani) gormek icin ExecuteQuery() methodunu kullanmaliyiz
        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()) {
            //getString methodunun icine ya istedigimiz kolonun ismini, ya da numarasini girmeliyiz.
            System.out.println(resultSet1.getString(1));
        }

        System.out.println("---------------------------");

        //2.Ornek : "region_id"nin 2'den buyuk oldugu "country_id" ve "country_name" degerlerini cagirin
        String sql2 = "select country_name,country_id from countries where region_id>2";
        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("country_name") + "--" + resultSet2.getString("country_id"));
        }

        System.out.println("----------------------------");

        //3.Ornek : "number_of_employees" degeri en kucuk olan satirin tum degerlerini cagirin
        String sql3 = "select * from companies where number_of_employees = (select min(number_of_employees) from companies)";

        ResultSet resultSet3 = st.executeQuery(sql3);

        while (resultSet3.next()) {
            System.out.println(resultSet3.getInt(1) + "--" + resultSet3.getString(2) + "--" + resultSet3.getInt(3));
        }



    }


}
