/**
 * Created by yishan on 28/7/16.
 */
import common.ProductInfoResolver;
import config.ConfFromProperties;

import java.io.IOException;
import java.util.ArrayList;

public class ProductInfoResolverTest {
    public static void main(String[] argv) throws IOException {
        ConfFromProperties conf = new ConfFromProperties("historyAnalysis.UserProfiling.properties");
        ProductInfoResolver productInfoResolver = new ProductInfoResolver(conf);
        ArrayList<String> cuttingList = (ArrayList<String>) productInfoResolver.getProductInfo("13696","cutting");
        for(int i = 0; i < cuttingList.size() ; i++){
            System.out.println(cuttingList.get(i));
        }

    }
}
