package historyAnalysis;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created by yishan on 27/7/16.
 */
public class UserProfiling {
    private static void loadConf(){

    }
    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName(UserProfiling.class.getName()).setMaster("yarn-cluster");
        JavaSparkContext sparkcontext = new JavaSparkContext(conf);
        JavaRDD<String> userLoggerFileRDD = sparkcontext.textFile("");

    }
}