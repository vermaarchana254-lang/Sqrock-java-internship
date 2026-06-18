import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc=
                new Scanner(System.in);

        AuthService auth=
                new AuthService();

        QuizService quiz=
                new QuizService();

        ResultService result=
                new ResultService();

        while(true){

            System.out.println("\n===== ONLINE QUIZ =====");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Exit");

            int choice=
                    sc.nextInt();

            switch(choice){

                case 1:

                    System.out.print("Username : ");
                    String u=sc.next();

                    System.out.print("Password : ");
                    String p=sc.next();

                    if(auth.register(
                            new User(u,p))){

                        System.out.println(
                                "Registered Successfully"
                        );
                    }

                    break;

                case 2:

                    System.out.print("Username : ");
                    u=sc.next();

                    System.out.print("Password : ");
                    p=sc.next();

                    int userId=
                            auth.login(u,p);

                    if(userId==-1){

                        System.out.println(
                                "Invalid Login"
                        );

                        break;
                    }

                    int score=
                            quiz.startQuiz();

                    int total=
                            quiz.getQuestions().size();

                    result.saveResult(
                            userId,
                            score,
                            total
                    );

                    System.out.println(
                            "\nScore : "
                                    +score
                                    +"/"
                                    +total
                    );

                    result.showResults(userId);

                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}