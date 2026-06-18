import java.sql.*;

public class ResultService {

    public void saveResult(
            int userId,
            int score,
            int total){

        try(Connection con=
                    DBConnection.getConnection()){

            double percentage=
                    ((double)score/total)*100;

            String sql=
                    "insert into results(user_id,score,total_questions,percentage) values(?,?,?,?)";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setInt(1,userId);
            ps.setInt(2,score);
            ps.setInt(3,total);
            ps.setDouble(4,percentage);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showResults(int userId){

        try(Connection con=
                    DBConnection.getConnection()){

            String sql=
                    "select * from results where user_id=?";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setInt(1,userId);

            ResultSet rs=
                    ps.executeQuery();

            while(rs.next()){

                System.out.println(
                        "Score : "
                                +rs.getInt("score")
                );

                System.out.println(
                        "Percentage : "
                                +rs.getDouble("percentage")
                );

                System.out.println("----------------");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}