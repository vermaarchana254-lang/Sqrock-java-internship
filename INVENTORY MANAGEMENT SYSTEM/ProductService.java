import java.sql.*;

public class ProductService {

    public void addProduct(Product product){

        try(Connection con=
                    DBConnection.getConnection()){

            String sql=
                    "insert into products(product_name,category,quantity,price,supplier_name) values(?,?,?,?,?)";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setString(1,product.getProductName());
            ps.setString(2,product.getCategory());
            ps.setInt(3,product.getQuantity());
            ps.setDouble(4,product.getPrice());
            ps.setString(5,product.getSupplierName());

            ps.executeUpdate();

            System.out.println("Product Added");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void viewProducts(){

        try(Connection con=
                    DBConnection.getConnection()){

            Statement st=
                    con.createStatement();

            ResultSet rs=
                    st.executeQuery("select * from products");

            while(rs.next()){

                System.out.println(
                        rs.getInt("product_id")
                                +" | "
                                +rs.getString("product_name")
                                +" | "
                                +rs.getString("category")
                                +" | Qty="
                                +rs.getInt("quantity")
                                +" | Price="
                                +rs.getDouble("price")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id){

        try(Connection con=
                    DBConnection.getConnection()){

            String sql=
                    "delete from products where product_id=?";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ps.setInt(1,id);

            ps.executeUpdate();

            System.out.println("Deleted");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}