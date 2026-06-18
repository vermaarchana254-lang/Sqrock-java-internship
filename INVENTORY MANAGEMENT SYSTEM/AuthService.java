import java.sql.*;

public class AuthService {

    public boolean register(User user){

        try(Connection con=
                    DBConnection.getConnection()){

            String sql=
                    "insert into users(username,password) values(?,?)";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());

            return ps.executeUpdate()>0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean login(
            String username,
            String password){

        try(Connection con=
                    DBConnection.getConnection()){

            String sql=
                    "select * from users where username=? and password=?";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs=
                    ps.executeQuery();

            return rs.next();

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}