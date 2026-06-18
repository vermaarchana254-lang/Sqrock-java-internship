import java.sql.*;

public class InventoryService {

    public void updateStock(
            int productId,
            int quantity,
            String action){

        try(Connection con=
                    DBConnection.getConnection()){

            if(action.equalsIgnoreCase("purchase")){

                String sql=
                        "update products set quantity=quantity+? where product_id=?";

                PreparedStatement ps=
                        con.prepareStatement(sql);

                ps.setInt(1,quantity);
                ps.setInt(2,productId);

                ps.executeUpdate();
            }

            if(action.equalsIgnoreCase("sale")){

                String sql=
                        "update products set quantity=quantity-? where product_id=?";

                PreparedStatement ps=
                        con.prepareStatement(sql);

                ps.setInt(1,quantity);
                ps.setInt(2,productId);

                ps.executeUpdate();
            }

            String record=
                    "insert into inventory_records(product_id,action_type,quantity) values(?,?,?)";

            PreparedStatement p=
                    con.prepareStatement(record);

            p.setInt(1,productId);
            p.setString(2,action);
            p.setInt(3,quantity);

            p.executeUpdate();

            System.out.println("Stock Updated");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void lowStockAlert(){

        try(Connection con=
                    DBConnection.getConnection()){

            Statement st=
                    con.createStatement();

            ResultSet rs=
                    st.executeQuery(
                            "select * from products where quantity<10"
                    );

            while(rs.next()){

                System.out.println(
                        "LOW STOCK : "
                                +rs.getString("product_name")
                                +" Qty="
                                +rs.getInt("quantity")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}