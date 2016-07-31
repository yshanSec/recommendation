package historyAnalysis;

import org.apache.spark.api.java.function.ReduceFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yishan on 31/7/16.
 */
public class ProductReduce implements ReduceFunction<HashMap<String, ArrayList<Tuple2<String, Integer>>>>{
    public HashMap<String, ArrayList<Tuple2<String, Integer>>> call(HashMap<String, ArrayList<Tuple2<String, Integer>>> value1, HashMap<String, ArrayList<Tuple2<String, Integer>>> value2){

    }
}
