import common.ProductInfoResolver;
import config.ConfFromProperties;

import java.io.IOException;

/**
 * Created by yishan on 28/7/16.
 */
public class ProductInfoResoverTest {
    public static void main(String[] argv) throws IOException{
        ConfFromProperties conf = new ConfFromProperties("UserProfiling.properties");
        ProductInfoResolver resolver = new ProductInfoResolver(conf);
    }
}
