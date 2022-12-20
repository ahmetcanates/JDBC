import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","54140370318a");
        Statement st = con.createStatement();

        /*
            Java'da methodlar return type olsa da olmasa da method olarak adlandirilir.
            SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandirilir.
         */

        //CallableStatement ile function cagirmayi parametrelendirecegiz.
        //1.Adim: Function kodunu yaz.
        String sql1 = "CREATE OR REPLACE FUNCTION toplamaF(x numeric, y numeric)\n" +
                "RETURNS numeric\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2.Adim : Function'u calistir.
        st.execute(sql1);

        //3.Adim: Function'u cagir.
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}");

        //4.Adim: Return icin registerOutParameter() methodunu, parametreler icin ise set() ... methodlarini uygula
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);

        //5.Adim: execute() methodu ile CallableStatement'i calistir.
        cst1.execute();

        //6.Adim: Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));

        //Koninin hacmini hesaplayan function yaziniz.

        //1.Adim: Function kodunu yaz.
        String sql2 = "CREATE OR REPLACE FUNCTION konininHacmiF(r NUMERIC, h NUMERIC)\n" +
                "RETURNS numeric\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2.Adim : Function'u calistir.
        st.execute(sql2);

        //3.Adim: Function'u cagir.
        CallableStatement cst2 = con.prepareCall("{? = call konininHacmiF(?, ?)}");

        //4.Adim: Return icin registerOutParameter() methodunu, parametreler icin ise set() ... methodlarini uygula
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);

        //5.Adim: execute() methodu ile CallableStatement'i calistir.
        cst2.execute();

        //6.Adim: Sonucu cagirmak icin return data type tipine gore
        System.out.printf("%.2f",cst2.getBigDecimal(1));


    }
}
