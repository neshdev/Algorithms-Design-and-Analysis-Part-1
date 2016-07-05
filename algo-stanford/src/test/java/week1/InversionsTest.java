package week1;

import  org.junit.*;

public class InversionsTest {

    @Test
    public void zero_Inversions_Test(){
        Integer[] a = {1, 2, 3, 4, 5, 6};
        int actual = Inversions.sortAndCount(a);
        Assert.assertEquals(0, actual);
    }

    @Test
    public void Six_elements_With_Max_15_Inversions_Test(){
        Integer[] a = {6, 5, 4, 3, 2, 1};
        int actual = Inversions.sortAndCount(a);
        Assert.assertEquals(15, actual);
    }
}
