package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by yishan on 30/7/16.
 */
public class PriceClassfier {
    private static int[] priceRange = {0, 3000, 5000, 8000, 12000, 20000, 100000000};
    public static String search(Integer price){
        int price_value = price.intValue();
        for(int i = 0; i < PriceClassfier.priceRange.length; i++){
            if(price_value >= PriceClassfier.priceRange[i] && price_value < PriceClassfier.priceRange[i+1]){
                return Integer.toString(i);
            }
        }
        return "-1";
    }
}
