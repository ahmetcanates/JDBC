import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {


    private static Connection connection;
    private static Statement statement;

    private static ResultSet resultSet;

    //1.Adim : Driver'a kaydol
    //2.Adim : Database'e baglan

    public static Connection connectToDatabase(String hostname, String dbName, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
             connection = DriverManager.getConnection("jdbc:postgresql://"+hostname+":5432/"+dbName,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(connection!=null) {
            System.out.println("Connection Success");
        }else {
            System.out.println("Connection Failed");
        }
        return connection;
    }

    //3.Adim : Statement olustur
    public static Statement createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    //4.Adim: Query calistir.

    public static boolean execute(String sql) {
        boolean isExecute;
        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isExecute;
    }

    //ExecuteQuery ve ExecuteUpdate methodlari odev..

    public static ResultSet executeQry(String sql) {
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static int executeUpdt(String sql) {
        int updateEdilenSatir;
        try {
            updateEdilenSatir = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateEdilenSatir;
    }


    //5.Adim: Baglanti ve statement'i kapat.
    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()) {
                System.out.println("Connection and statement is closed!");
            }else {
                System.out.println("Connection and statement is not closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Table olusturan method

    public static void createTable(String tableName, String... columnName_dataType) {
        StringBuilder columnName_dataValue = new StringBuilder("");

        for(String w : columnName_dataType) {
            columnName_dataValue.append(w).append(",");
        }

        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);

        try {
            statement.execute("CREATE TABLE "+tableName+"("+columnName_dataValue+")");
            System.out.println("Table "+tableName+" successfully created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //table'a data girme yani insert into

    public static void insertDataIntoTable(String tableName, String... columnName_value) {

        StringBuilder columnNames = new StringBuilder("");
        StringBuilder values = new StringBuilder("");

        for(String w : columnName_value) {
            columnNames.append(w.split(" ")[0]).append(",");
            values.append(w.split(" ")[1]).append(",");
        }

        columnNames.deleteCharAt(columnNames.lastIndexOf(","));
        values.deleteCharAt(values.lastIndexOf(","));

        String query = "INSERT INTO " + tableName + "(" + columnNames + ") VALUES(" + values + ")";
        execute(query);

        System.out.println("DATA" + tableName + "tablosuna eklenmistir.");
    }

    //Sütun Değerlerini List içerisine alan method
    public static List<Object> getColumnList(String columnName, String tableName) {

        List<Object> columnData = new ArrayList<>();

        String query = "SELECT " + columnName + " FROM " + tableName;
        executeQry(query);
        try{
            while(resultSet.next()) {
                columnData.add(resultSet.getObject(columnName));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return columnData;

    }






}
