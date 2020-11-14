package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listofmap_example {

    String dbUrl = "jdbc:oracle:thin:@54.157.131.161:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("Select * from regions");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //List for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_PRES");

        System.out.println(row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name", "Nina");
        row2.put("last_name", "Kochar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");

        System.out.println(row2.toString());

        System.out.println(row2.get("salary"));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven's last name directly from the list
        queryData.get(0).get("last_name");
        System.out.println("Steven's Last Name = " +queryData.get(0).get("last_name"));


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test
    public void MetaDataExample2() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from employees\n" +
                "where rownum <6");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //List for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        //move to first row
        resultSet.next();

        Map<String,Object> row1 = new HashMap<>();
        row1.put(rsMetadata.getColumnName(1), resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2), resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3), resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4), resultSet.getString(4));

        System.out.println(row1.toString());

        //move to second row
        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();
        row2.put(rsMetadata.getColumnName(1), resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2), resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3), resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4), resultSet.getString(4));

        System.out.println(row2.toString());

        System.out.println(row2.get("SALARY"));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven's last name directly from the list
        queryData.get(0).get("LAST_NAME");
        System.out.println("Steven's Last Name = " +queryData.get(0).get("LAST_NAME"));


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
