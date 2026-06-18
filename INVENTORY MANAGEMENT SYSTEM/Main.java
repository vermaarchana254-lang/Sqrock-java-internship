import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        AuthService auth=
                new AuthService();

        ProductService product=
                new ProductService();

        InventoryService inventory=
                new InventoryService();

        while(true){

            System.out.println("\n===== INVENTORY SYSTEM =====");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Exit");

            int choice=sc.nextInt();

            switch(choice){

                case 1:

                    System.out.print("Username : ");
                    String u=sc.next();

                    System.out.print("Password : ");
                    String p=sc.next();

                    auth.register(
                            new User(u,p)
                    );

                    break;

                case 2:

                    System.out.print("Username : ");
                    u=sc.next();

                    System.out.print("Password : ");
                    p=sc.next();

                    if(!auth.login(u,p)){

                        System.out.println(
                                "Invalid Login"
                        );

                        break;
                    }

                    while(true){

                        System.out.println(
                                "\n1.Add Product"
                        );

                        System.out.println(
                                "2.View Products"
                        );

                        System.out.println(
                                "3.Delete Product"
                        );

                        System.out.println(
                                "4.Purchase Stock"
                        );

                        System.out.println(
                                "5.Sale Stock"
                        );

                        System.out.println(
                                "6.Low Stock Alert"
                        );

                        System.out.println(
                                "7.Logout"
                        );

                        int ch=sc.nextInt();

                        if(ch==7)
                            break;

                        switch(ch){

                            case 1:

                                System.out.print(
                                        "Name : "
                                );

                                String name=sc.next();

                                System.out.print(
                                        "Category : "
                                );

                                String cat=sc.next();

                                System.out.print(
                                        "Qty : "
                                );

                                int qty=sc.nextInt();

                                System.out.print(
                                        "Price : "
                                );

                                double price=sc.nextDouble();

                                System.out.print(
                                        "Supplier : "
                                );

                                String supplier=sc.next();

                                product.addProduct(
                                        new Product(
                                                name,
                                                cat,
                                                qty,
                                                price,
                                                supplier
                                        )
                                );

                                break;

                            case 2:

                                product.viewProducts();
                                break;

                            case 3:

                                System.out.print(
                                        "Product ID : "
                                );

                                product.deleteProduct(
                                        sc.nextInt()
                                );

                                break;

                            case 4:

                                System.out.print(
                                        "Product ID : "
                                );

                                int id=sc.nextInt();

                                System.out.print(
                                        "Quantity : "
                                );

                                qty=sc.nextInt();

                                inventory.updateStock(
                                        id,
                                        qty,
                                        "purchase"
                                );

                                break;

                            case 5:

                                System.out.print(
                                        "Product ID : "
                                );

                                id=sc.nextInt();

                                System.out.print(
                                        "Quantity : "
                                );

                                qty=sc.nextInt();

                                inventory.updateStock(
                                        id,
                                        qty,
                                        "sale"
                                );

                                break;

                            case 6:

                                inventory.lowStockAlert();
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