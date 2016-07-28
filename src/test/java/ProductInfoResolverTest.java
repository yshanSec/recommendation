/**
 * Created by yishan on 28/7/16.
 */
import common.ProductInfoResolver;
import config.ConfFromProperties;

import java.io.IOException;

public class ProductInfoResolverTest {
    public static void main(String[] argv) throws IOException {
        ConfFromProperties conf = new ConfFromProperties("UserProfiling.properties");
        ProductInfoResolver productInfoResolver = new ProductInfoResolver(conf);
        System.out.println(productInfoResolver.getProductInfo("13696","color"));
    }
}
