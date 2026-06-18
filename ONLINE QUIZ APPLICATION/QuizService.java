import java.sql.*;
import java.util.*;

public class QuizService {

    public List<Question> getQuestions(){

        List<Question> list=
                new ArrayList<>();

        try(Connection con=
                    DBConnection.getConnection()){

            String sql=
                    "select * from questions";

            Statement st=
                    con.createStatement();

            ResultSet rs=
                    st.executeQuery(sql);

            while(rs.next()){

                Question q=
                        new Question(
                                rs.getInt("id"),
                                rs.getString("question"),
                                rs.getString("option1"),
                                rs.getString("option2"),
                                rs.getString("option3"),
                                rs.getString("option4"),
                                rs.getInt("correct_answer")
                        );

                list.add(q);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public int startQuiz(){

        Scanner sc=
                new Scanner(System.in);

        List<Question> questions=
                getQuestions();

        int score=0;

        for(Question q:questions){

            System.out.println("\n"+q.getQuestion());

            System.out.println("1. "+q.getOption1());
            System.out.println("2. "+q.getOption2());
            System.out.println("3. "+q.getOption3());
            System.out.println("4. "+q.getOption4());

            System.out.print("Answer : ");

            int ans=sc.nextInt();

            if(ans==q.getCorrectAnswer()){
                score++;
            }
        }

        return score;
    }
}