package orangeHRM;

import org.testng.annotations.Test;

import java.util.List;


public class test1 {
    @Test
    public void test() {
        System.out.println("Hello, World!");
        String strg = "aa\nbb\ncc\ndd\nee";
        int[] count = new int[strg.length()];
        int c1=0;
        char[] arrayStrg= strg.toCharArray();
        for (int i = 0; i<arrayStrg.length; i++) {
            if (arrayStrg[i] == '\n') {
                count[c1] = i;
                c1++;
            }
        }
        System.out.println(strg.substring(count[c1-2]));
        System.out.println("---------------------------------");
    }
}
