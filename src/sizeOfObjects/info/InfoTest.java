package sizeOfObjects.info;

import sizeOfObjects.info.MethodsInfo;
import sizeOfObjects.info.ObjectInfo;
import sizeOfObjects.test.SubArrayList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * @author dmifed
 */
public class InfoTest {
    public static void main(String[] args) {
        SubArrayList<Integer> subArrayList = new SubArrayList<>();
        subArrayList.init(10, "A");
        Date d = new Date();
        TreeMap<String, String> map = new TreeMap<>();
        List<String> strings = new ArrayList<>();
        ObjectInfo oI = new ObjectInfo();
        int[] a = new int[3];
        int b = 9;

        Object[] objects = new Object[6];
        objects[0] = subArrayList;
        objects[1] = d;
        objects[2] = map;
        objects[3] = strings;
        objects[4] = a;
        objects[5] = null;

        for (int i = 0; i < objects.length; i++){
            System.out.println("\n starting #" + i);
            oI.getInfo(objects[i]);
            System.out.println(oI.getMethodsNameReturnType("void"));
        }
        oI.getInfo(b);
        oI.getNonDeprecatedInfo(b);
        System.out.println(oI.getMethodsNameReturnType("int"));



    }
}
