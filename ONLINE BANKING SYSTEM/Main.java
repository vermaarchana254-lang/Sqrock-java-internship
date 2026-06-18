import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        AuthService auth=
                new AuthService();

        BankingService bank=
                new BankingService();

        while(true){

            System.out.println("\n1.Register");
            System.out.println("2.Login");
            System.out.println("3.Exit");

            int choice=sc.nextInt();

            switch(choice){

                case 1:

                    System.out.print("Username : ");
                    String uname=sc.next();

                    System.out.print("Password : ");
                    String pass=sc.next();

                    User user=
                            new User(uname,pass);

                    if(auth.register(user))
                        System.out.println("Registered");
                    else
                        System.out.println("Failed");

                    break;

                case 2:

                    System.out.print("Username : ");
                    uname=sc.next();

                    System.out.print("Password : ");
                    pass=sc.next();

                    int userId=
                            auth.login(uname,pass);

                    if(userId==-1){

                        System.out.println(
                                "Invalid Login"
                        );

                        break;
                    }

                    System.out.println(
                            "Login Success"
                    );

                    while(true){

                        System.out.println("\n1.Create Account");
                        System.out.println("2.Check Balance");
                        System.out.println("3.Deposit");
                        System.out.println("4.Withdraw");
                        System.out.println("5.Transfer");
                        System.out.println("6.Transaction History");
                        System.out.println("7.Logout");

                        int ch=sc.nextInt();

                        if(ch==7)
                            break;

                        switch(ch){

                            case 1:

                                System.out.print("Holder Name : ");
                                String name=sc.next();

                                System.out.print("Type : ");
                                String type=sc.next();

                                System.out.print("Initial Balance : ");
                                double bal=sc.nextDouble();

                                bank.createAccount(
                                        userId,
                                        name,
                                        type,
                                        bal
                                );
                                break;

                            case 2:

                                System.out.print("Account No : ");

                                bank.checkBalance(
                                        sc.nextInt()
                                );
                                break;

                            case 3:

                                System.out.print("Account No : ");
                                int acc=sc.nextInt();

                                System.out.print("Amount : ");
                                double dep=sc.nextDouble();

                                bank.deposit(acc,dep);
                                break;

                            case 4:

                                System.out.print("Account No : ");
                                acc=sc.nextInt();

                                System.out.print("Amount : ");
                                double wd=sc.nextDouble();

                                bank.withdraw(acc,wd);
                                break;

                            case 5:

                                System.out.print("From : ");
                                int from=sc.nextInt();

                                System.out.print("To : ");
                                int to=sc.nextInt();

                                System.out.print("Amount : ");
                                double amt=sc.nextDouble();

                                bank.transfer(
                                        from,
                                        to,
                                        amt
                                );
                                break;

                            case 6:

                                System.out.print("Account No : ");

                                bank.transactionHistory(
                                        sc.nextInt()
                                );

                                break;
                        }
                    }

                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}