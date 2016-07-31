package historyAnalysis;


import common.ProductInfoResolver;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import config.ConfFromProperties;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yishan on 27/7/16.
 */
public class UserProfiling {
    private static String baseDir;
    private static ConfFromProperties userProfilingConf;
    private static Logger log = Logger.getLogger(UserProfiling.class.getName());
    private static String master;
    private static String productLoggerPath;
    private static String keywordsLoggerPath;
    private static String storePath;
    public static Broadcast<ProductInfoResolver> productInfoResolverBroeadcast;

    private static void loadConf() throws IOException{
        // get configuration file
        String propertiesDefaultSuffix = ".properties";
        UserProfiling.baseDir = System.getProperty("user.dir");
        String sparkPropertyFile = UserProfiling.class.getName().concat(propertiesDefaultSuffix);
        UserProfiling.userProfilingConf = new ConfFromProperties(sparkPropertyFile);
    }

    private static void init(){
        UserProfiling.productLoggerPath = userProfilingConf.getValue("productLoggerPath");
        UserProfiling.keywordsLoggerPath = userProfilingConf.getValue("productLoggerPath");
        UserProfiling.master = userProfilingConf.getValue("master");
        UserProfiling.storePath = userProfilingConf.getValue("storePath");
    }
    public static void main(String[] args) throws IOException{
        UserProfiling.loadConf();
        UserProfiling.init();

        SparkConf conf = new SparkConf().setAppName(UserProfiling.class.getName()).setMaster(master);
        JavaSparkContext sparkcontext = new JavaSparkContext(conf);
        //broadcast value
        UserProfiling.productInfoResolverBroeadcast = sparkcontext.broadcast(new ProductInfoResolver(UserProfiling.userProfilingConf));

        JavaRDD<String> userLoggerFileRDD = sparkcontext.textFile(UserProfiling.productLoggerPath);
        JavaPairRDD<String, HashMap<String, ArrayList<Tuple2<String, Integer>>>> elementParis = userLoggerFileRDD.flatMapToPair(new ProductFlatMap());
        elementParis.reduceByKey(new ProductReduce());
    }
}