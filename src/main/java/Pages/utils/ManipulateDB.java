package Pages.utils;

import AddingNewRequests.addReqDetails;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class ManipulateDB
{

    public static String  executeQuery(String sql, String colName) {
        // JDBC driver name and database URL
        String JDBC_DRIVER = "net.sourceforge.jtds.jdbc.Driver"; //"com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String DB_URL = "jdbc:jtds:sqlserver://10.10.2.106:1433;databasename=MDAC.Retirement;encrypt=true;trustserverCertificate=true";//"jdbc:sqlserver://10.10.2.106:1433;DatabaseName='MDAC.Retirement';encrypt=true;trustServerCertificate=true;";
        String UN ="sa";
        String PW ="saadmin!23";
        String retValue ="-1";
        Connection conn = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, UN, PW);

            Statement stmt = null;
            ResultSet rs = null;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // Process the results
            while (rs.next()) {
                retValue = rs.getString(colName);
                System.out.println("retValue: " + retValue);
            }

            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return retValue;
    }

    public static void main(String[]args) throws IOException, ParseException, InterruptedException {
        ManipulateDB.executeQuery("Select *  FROM RequestTotals where RequestId = 6798 order by 1 desc","TotalRemainingLeaves");
        ManipulateDB.executeQuery("Select *  FROM RequestTotals where RequestId = 6798 order by 1 desc","TotaEndOfServiceCount");
        ManipulateDB.executeQuery("Select *  FROM RequestTotals where RequestId = 6798 order by 1 desc","TotalReadyStatus");
        //

        //obj.tryOkay();
    }
}
