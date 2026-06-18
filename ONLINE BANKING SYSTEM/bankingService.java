import java.sql.*;

public class BankingService {

    public void createAccount(
            int userId,
            String holderName,
            String type,
            double balance){

        try(Connection con=
                DBConnection.getConnection()){

            String sql=
                    "insert into accounts(user_id,holder_name,account_type,balance) values(?,?,?,?)";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setInt(1,userId);
            ps.setString(2,holderName);
            ps.setString(3,type);
            ps.setDouble(4,balance);

            ps.executeUpdate();

            System.out.println("Account Created");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void checkBalance(int accountNo){

        try(Connection con=
                DBConnection.getConnection()){

            String sql=
                    "select balance from accounts where account_no=?";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setInt(1,accountNo);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                System.out.println(
                        "Balance : "
                                + rs.getDouble("balance")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deposit(
            int accountNo,
            double amount){

        try(Connection con=
                DBConnection.getConnection()){

            String sql=
                    "update accounts set balance=balance+? where account_no=?";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setDouble(1,amount);
            ps.setInt(2,accountNo);

            ps.executeUpdate();

            addTransaction(
                    accountNo,
                    "Deposit",
                    amount
            );

            System.out.println("Deposit Successful");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void withdraw(
            int accountNo,
            double amount){

        try(Connection con=
                DBConnection.getConnection()){

            String check=
                    "select balance from accounts where account_no=?";

            PreparedStatement ps=
                    con.prepareStatement(check);

            ps.setInt(1,accountNo);

            ResultSet rs=
                    ps.executeQuery();

            if(rs.next()){

                double balance=
                        rs.getDouble("balance");

                if(balance<amount){

                    System.out.println(
                            "Insufficient Balance"
                    );

                    return;
                }
            }

            String sql=
                    "update accounts set balance=balance-? where account_no=?";

            PreparedStatement p=
                    con.prepareStatement(sql);

            p.setDouble(1,amount);
            p.setInt(2,accountNo);

            p.executeUpdate();

            addTransaction(
                    accountNo,
                    "Withdrawal",
                    amount
            );

            System.out.println(
                    "Withdraw Successful"
            );

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void transfer(
            int from,
            int to,
            double amount){

        withdraw(from,amount);
        deposit(to,amount);

        addTransaction(
                from,
                "Transfer Sent",
                amount
        );

        addTransaction(
                to,
                "Transfer Received",
                amount
        );

        System.out.println(
                "Transfer Successful"
        );
    }

    private void addTransaction(
            int accountNo,
            String type,
            double amount){

        try(Connection con=
                DBConnection.getConnection()){

            String sql=
                    "insert into transactions(account_no,transaction_type,amount) values(?,?,?)";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setInt(1,accountNo);
            ps.setString(2,type);
            ps.setDouble(3,amount);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void transactionHistory(
            int accountNo){

        try(Connection con=
                DBConnection.getConnection()){

            String sql=
                    "select * from transactions where account_no=?";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setInt(1,accountNo);

            ResultSet rs=
                    ps.executeQuery();

            while(rs.next()){

                System.out.println(
                        rs.getString("transaction_type")
                                +" | "
                                +rs.getDouble("amount")
                                +" | "
                                +rs.getTimestamp("transaction_date")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}