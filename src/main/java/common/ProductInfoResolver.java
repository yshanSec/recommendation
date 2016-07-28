package common;

import config.ConfFromProperties;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yishan on 28/7/16.
 */
public class ProductInfoResolver {
    // mysql
    private static String mysqlURLPrefix = "jdbc:mysql://";
    private Connection conn = null;
    private Statement statement = null;
    // sql query
    private String ProductQuery = "select id, tags, ex_color, price, brand_id, sub_category, category from deja.product";
    // log
    private static Logger log = Logger.getLogger(ProductInfoResolver.class.getName());
    //product
    HashMap productMap = null;

    class ProductHashMap extends HashMap{
        public ProductHashMap(ResultSet resultSet) throws SQLException{
            this.put("id", resultSet.getString("id"));
            this.put("price", resultSet.getString("price"));
            this.put("brand", resultSet.getString("brand"));
            this.put("category", resultSet.getString("category"));
            this.put("subcategory", resultSet.getString("sub_category"));
            this.put("ex_color", resultSet.getString("ex_color"));

            JSONObject tags = new JSONObject(resultSet.getString("tags"));
            this.put("pattern", tags.getJSONArray("3").getString(0));

            ArrayList<String> cutting = null;
            JSONArray cuttingJsonArray = tags.getJSONArray("5");
            for(int i = 0; i < cuttingJsonArray.length(); i++){
                cutting.add(cuttingJsonArray.getString(i));
            }
            this.put("cutting", cutting);
        }
    }

    public ProductInfoResolver(ConfFromProperties conf){
        String host = conf.getValue("host");
        String port = conf.getValue("port");
        String db =conf.getValue("db");
        String username = conf.getValue("username");
        String passwd = conf.getValue("passwd");
        // "jdbc:mysql://targethost:port/targetdb"
        String mysqlHost = ProductInfoResolver.mysqlURLPrefix.concat(host).concat(":").concat(port).concat("/").concat(db);
        //return results
        ResultSet resultSet = null;

        try{
            this.conn = DriverManager.getConnection(mysqlHost,username,passwd);
            this.statement = conn.createStatement();
            resultSet = this.statement.executeQuery(this.ProductQuery);
            while(resultSet.next()){
                this.productMap.put(resultSet.getLong("id"),new ProductHashMap(resultSet));
            }
        } catch (SQLException e){
            this.log.error("MySQL Connection Error:" + e);
        }

    }
    public Object getProductInfo(String productId,String fieldName){
        ProductHashMap productHashMap = (ProductHashMap) this.productMap.get(productId);
        if(fieldName=="brand"){
            return "b".concat((String) productHashMap.get(fieldName));
        }
        else if(fieldName=="price"){
            return "p".concat((String) productHashMap.get(fieldName));
        }
        else{
            return (String) productHashMap.get(fieldName);
        }
    }
}