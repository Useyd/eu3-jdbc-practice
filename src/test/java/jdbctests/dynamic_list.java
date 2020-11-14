package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@54.157.131.161:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample2() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("select * from countries");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //List for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsMetadata.getColumnCount();

        //loop through each row
        //while loop will work for rows
        while(resultSet.next()){
            Map<String,Object> row = new HashMap<>();

            //put data to rows with for loop dynamically
            //for loop will work for columns
            for (int i = 1; i <= colCount; i++) {

                row.put(rsMetadata.getColumnName(i), resultSet.getObject(i));

            }


            //add your map to your list
            queryData.add(row);

        }


        //print the result
        for (Map<String, Object> row : queryData) {

            System.out.println(row.toString());

        }





        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }




}
