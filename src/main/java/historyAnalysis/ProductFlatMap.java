package historyAnalysis;

import common.ProductInfoResolver;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.json.JSONArray;
import scala.Tuple2;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yishan on 28/7/16.
 */
public class ProductFlatMap implements PairFlatMapFunction<String, String, String> {

    public Iterable<Tuple2<String, String>> call(String line){
        ArrayList<Tuple2<String, String>> result = null;
        // process line
        String key;
        String[] productVisitInfo = line.split("\t");
        key = productVisitInfo[0];

        // get all fields
        JSONObject json = new JSONObject(productVisitInfo[2]);
        JSONArray productIds = json.getJSONArray("product_ids");
        ProductInfoResolver productInfoResolver = UserProfiling.productInfoResolverBroeadcast.value();
        for(int i = 0; i < productIds.length(); i++){
            String productId = productIds.getString(i);
            String category = (String) productInfoResolver.getProductInfo(productId, "category");
            result.add(new Tuple2<String, String>(key, category));

            String subcategory = (String) productInfoResolver.getProductInfo(productId, "subcategory");
            result.add(new Tuple2<String, String>(key, subcategory));

            String color = (String) productInfoResolver.getProductInfo(productId, "color");
            result.add(new Tuple2<String, String>(key, color));

            String brand = (String) productInfoResolver.getProductInfo(productId, "brand");
            result.add(new Tuple2<String, String>(key, brand));

            String price = (String) productInfoResolver.getProductInfo(productId, "price");
            result.add(new Tuple2<String, String>(key, subcategory.concat("\t").concat(price)));

            ArrayList<String> cutting = (ArrayList<String>) productInfoResolver.getProductInfo(productId, "cutting");
            for(int j = 0; i< cutting.size(); j++){
                result.add(new Tuple2<String, String>(key, cutting.get(j)));
            }
        }
        return result;
    }
}
