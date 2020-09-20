package com.connection;


import com.models.*;
import java.sql.*;
import java.util.ArrayList;

public class SQLite_library implements UserSQL {
    private final SQLiteConnector sqlConnector = new SQLiteConnector();
    @Override
    public void setupClient(User user) {
        String sqlLibrary = "Select UserLibrary.UserId, Library.LibraryId, Author from Library\n" +
                "Inner Join UserLibrary\n" +
                "On Library.LibraryId = UserLibrary.LibraryId \n" +
                "Where UserLibrary.UserId in (select UserId from User where Login=? " +
                "and Password=?)";

        String sqlBook = "Select * from Book Where LibraryId = ?";
        String sqlUserName = "Select Name from User where Login=? and Password=?";

        String login = user.getLogin();
        String password = user.getPassword();
        ResultSet rs = null, rs2 = null;
        Connection conn = null;
        PreparedStatement pstmt0 = null,pstmt1 = null, pstmt2 = null;

        try {
            // соединение с бд
            conn = this.sqlConnector.connect();
            // установка режима auto-commit в false
            conn.setAutoCommit(false);
            pstmt0 = conn.prepareStatement(sqlUserName);
            pstmt1 = conn.prepareStatement(sqlLibrary);
            pstmt2 = conn.prepareStatement(sqlBook);

            pstmt0.setString(1,login);
            pstmt0.setString(2,password);

            rs=pstmt0.executeQuery();
            while(rs.next()){
                user.setName(rs.getString("Name"));
            }
            // установка значения первого параметра
            pstmt1.setString(1, login);
            pstmt1.setString(2, password);

            // получение результат выполнения запроса
            rs = pstmt1.executeQuery();
            int i = 0;
            // проход по строкам результата выполнения запроса
            user.setList(new ArrayList<>());
            while (rs.next()) {
                // добавление списка в обьект класса Client
                user.addListItem(new Library(rs.getString("Author")));
                user.getListItem(i).setId(rs.getInt("LibraryId"));
                // установка значения первого параметра
                pstmt2.setInt(1, user.getListItem(i).getId());
                rs2 = pstmt2.executeQuery();
                // проход по строкам результата выполнения запроса
                int j = 0;

                while (rs2.next()) { // чтения строки результата выполнения запроса
                    // добавление автомобиля в список
                    user.getListItem(i).add(new Book(rs2.getString("Name"),
                            rs2.getInt("Year"), rs2.getInt("PageCount"), rs2.getInt("Price")));
                    user.getListItem(i).getBook(j++).setId(rs2.getInt("BookId"));
                }

                i++;
            }

            // подтверждение изменений, внесенных транзакцией
            conn.commit();
        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (pstmt0 != null) {
                    pstmt0.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
            }
        }

    }

    @Override
    public boolean isClientExist(String login, String password) {
        String sql = "Select UserId from User \n" +
                "Where Login = ? AND Password = ?";
        boolean result = false;
        try (Connection conn = this.sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            // получение результата выполнения запроса
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { // если в ответе есть строка значит существует
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
