package week1;

import  org.junit.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class InversionsTest {

    @Test
    public void zero_Inversions_Test(){
        Integer[] a = {1, 2, 3, 4, 5, 6};
        double actual = Inversions.sortAndCount(a);
        Assert.assertEquals(0, actual, 0.001);
    }

    @Test
    public void Six_elements_With_Max_15_Inversions_Test(){
        Integer[] a = {6, 5, 4, 3, 2, 1};
        double actual = Inversions.sortAndCount(a);
        Assert.assertEquals(15, actual, 0.001);
    }

    @Test
    public void load_elements_From_File_Inversions_Test(){
        ArrayList<Integer> a = new ArrayList<>();

        String location = "C:/coursera/algo-stanford/algo-stanford/src/test/java/resources/IntegerArray.txt";

        try{
            Files.lines(Paths.get(location)).forEach( s-> a.add(Integer.parseInt(s)));
            System.out.println(a.size());
            Integer[] arr = new Integer[a.size()];
            arr = a.toArray(arr);
            double actual = Inversions.sortAndCount(arr);
            Assert.assertEquals(2407905288.0, actual, 0.001);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
