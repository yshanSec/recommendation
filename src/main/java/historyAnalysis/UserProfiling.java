package historyAnalysis;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import utils.ConfigLoader;
/**
 * Created by yishan on 27/7/16.
 */
public class UserProfiling {
    private static void loadConf(){
        String currentWorkDirectory = System.getProperty("user.dir");

    }
    public static void main(String[] args){
        UserProfiling.loadConf();

        SparkConf conf = new SparkConf().setAppName(UserProfiling.class.getName()).setMaster("yarn-cluster");
        JavaSparkContext sparkcontext = new JavaSparkContext(conf);
        JavaRDD<String> userLoggerFileRDD = sparkcontext.textFile("");

    }
}