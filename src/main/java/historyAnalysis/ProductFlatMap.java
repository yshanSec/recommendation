package historyAnalysis;

import common.ProductInfoResolver;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.json.JSONArray;
import scala.Tuple2;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yishan on 28/7/16.
 */
public class ProductFlatMap implements PairFlatMapFunction<String, String, HashMap<String, ArrayList<Tuple2<String, Integer>>>> {

    public Iterable<Tuple2<String, HashMap<String, ArrayList<Tuple2<String, Integer>>>>> call(String line){

        ArrayList<Tuple2<String, HashMap<String, ArrayList<Tuple2<String, Integer>>>>> result = new ArrayList<Tuple2<String, HashMap<String, ArrayList<Tuple2<String, Integer>>>>>();
        // process line
        String key;
        String[] productVisitInfo = line.split("\t");
        key = productVisitInfo[0];

        // get all fields
        JSONObject json = new JSONObject(productVisitInfo[2]);
        JSONArray productIds = json.getJSONArray("product_ids");
        ProductInfoResolver productInfoResolver = UserProfiling.productInfoResolverBroeadcast.value();



        for(int i = 0; i < productIds.length(); i++){
            HashMap<String, ArrayList<Tuple2<String, Integer>>> productInfo = new HashMap<String, ArrayList<Tuple2<String, Integer>>>();
            String productId = productIds.getString(i);
            String category = (String) productInfoResolver.getProductInfo(productId, "category");
            ArrayList<Tuple2<String, Integer>> categoryList = new ArrayList<Tuple2<String, Integer>>();
            categoryList.add(new Tuple2<String, Integer>(category, 1));
            productInfo.put("category", categoryList);

            String subcategory = (String) productInfoResolver.getProductInfo(productId, "subcategory");
            ArrayList<Tuple2<String, Integer>> subcategoryList = new ArrayList<Tuple2<String, Integer>>();
            subcategoryList.add(new Tuple2<String, Integer>(subcategory, 1));
            productInfo.put("subcategory", subcategoryList);

            String color = (String) productInfoResolver.getProductInfo(productId, "color");
            ArrayList<Tuple2<String, Integer>> colorList = new ArrayList<Tuple2<String, Integer>>();
            subcategoryList.add(new Tuple2<String, Integer>(color, 1));
            productInfo.put("color", colorList);


            String brand = (String) productInfoResolver.getProductInfo(productId, "brand");
            ArrayList<Tuple2<String, Integer>> brandList = new ArrayList<Tuple2<String, Integer>>();
            subcategoryList.add(new Tuple2<String, Integer>(brand, 1));
            productInfo.put("color", brandList);


            String price = (String) productInfoResolver.getProductInfo(productId, "price");
            ArrayList<Tuple2<String, Integer>> priceList = new ArrayList<Tuple2<String, Integer>>();
            subcategoryList.add(new Tuple2<String, Integer>(subcategory.concat("\t").concat(price), 1));
            productInfo.put("color", brandList);


            ArrayList<Tuple2<String, Integer>> cuttingList = new ArrayList<Tuple2<String, Integer>>();
            ArrayList<String> cutting = (ArrayList<String>) productInfoResolver.getProductInfo(productId, "cutting");
            for(int j = 0; j < cutting.size(); j++){
                cuttingList.add(new Tuple2<String, Integer>(subcategory.concat("\t").concat(price), 1));
            }
            productInfo.put("cutting", cuttingList);
            result.add(new Tuple2<String, HashMap<String, ArrayList<Tuple2<String, Integer>>>>(key, productInfo));
        }
        return result;
    }
}
