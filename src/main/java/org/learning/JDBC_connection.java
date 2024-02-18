package org.learning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBC_connection {
    static Connection con=null;
    public static Connection getConnection() {
        try{
            Properties pro = new Properties();
            pro.load(new FileInputStream("src/main/java/org/learning/config.properties"));
            System.out.println(pro.getProperty("DB_URL"));
            con = DriverManager.getConnection(pro.getProperty("DB_URL"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    public static boolean logins_checking(Connection con,String query){
        try {
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);
            result.next();
            result.getInt("val");
            if(result.getInt("val")==0){
                return false;
            }
            return true;
        }

        catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }
    public static Bank.Account fecting_details(Connection con, long accountno){
        Bank.Account acc  = null;
        try {
            Statement st = con.createStatement();
            String sql = "select * from bankaccount where accountno = "+accountno +" ;";
            String check = "select count(*) from bankaccount where accountno = "+accountno +" ;";
            ResultSet result = st.executeQuery(check);
            result.next();
            if(result.getInt(1)>0) {
                ResultSet resultSet = st.executeQuery(sql);
                resultSet.next();
                long accountNo = resultSet.getLong("accountNo");
                String name = resultSet.getString("username");
                String email = resultSet.getString("email");
                long phoneNo = resultSet.getLong("phoneNo");
                String accountType = resultSet.getString("accounttype");
                String cardType = resultSet.getString("cardtype");
                double balance = (double) resultSet.getLong("balance");
                return new Bank.Account(accountNo, name, balance, email, phoneNo, accountType.equals("savingsacc"), cardType.equals("debitcard"));
            }
            else throw new InvalidAccountNumber("Invalid Account Number");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (InvalidAccountNumber ignored) {

        }
        return acc;
    }

    public static long retrivelastaccountno(Connection con){
        try {
            Statement st = con.createStatement();
            ResultSet resultSet = st.executeQuery("select accountno from bankaccount order by id desc");
            resultSet.next();
            return resultSet.getLong("accountno");
        }
        catch (Exception ignored){

        }
        return 0;
    }
    public static boolean signup(Connection con,String username,String password){
        try {
            String sql = "Insert into userdetails(username,password) values (?,?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);
            int result = st.executeUpdate();

            return result>0;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        }
        return false;
    }

    public static int addaccount_details(Connection con, long accountNo, double balance, String username, String email, long phoneNo, String savingsacc, String debitcard) {

        try{
            String sql = "insert into bankaccount(accountNo , balance , username , email , phoneNo , accounttype , cardtype) values(?,?,?,?,?,?,?);";

            PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1,accountNo);
            st.setDouble(2,balance);
            st.setString(3,username);
            st.setString(4,email);
            st.setLong(5,phoneNo);
            st.setString(6,savingsacc);
            st.setString(7,debitcard);
            return st.executeUpdate();


        }
        catch (SQLException ignored){

        }
        return 0;
    }
}
