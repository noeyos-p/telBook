package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static Connection dbConn;
    // Spring 에서 이걸써요
    public static Connection getConnection() {
        // db 연결시도
        if (dbConn == null) {
            try {
                String dbDriver = "com.mysql.cj.jdbc.Driver";
                String dbUrl = "jdbc:mysql://localhost:3306/tel_book_db";
                String dbUser = "root";
                String dbPassword = "1111";
                Class.forName(dbDriver);
                // dbDriver 라는 애가 있는지 없는지 확인함
                dbConn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                // 연결 성공한 경우
                System.out.println("DB 연결 성공");
            } catch (ClassNotFoundException e) {
                System.out.println("DB 연결 실패_1");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("DB 연결 실패_2");
                e.printStackTrace();
                // 오류를 잡는 애 catch
            }
        }
        return dbConn;
    }
}
