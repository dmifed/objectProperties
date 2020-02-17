package sizeOfObjects;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;


/**
 * @author dmifed
 */
class SuperObjectSize {
    private Util util;
    private ObjectSize objectSize;

    void init(Util util, ObjectSize objectSize) {
        this.util = util;
        this.objectSize = objectSize;
    }

    long getSizeOfSuperObject(Object o, int deep){
        ++deep;
        long size = 0;
        Class<?> c = o.getClass().getSuperclass();
        if(c == null) return 0;
        if(c.getName().endsWith("Object")){
            System.out.println(util.printSize(deep, c, 8, "superFinal"));
            return 8;
        }else {
            if(!Modifier.isAbstract(c.getSuperclass().getModifiers()) ){
                Constructor<?> superConstructor;
                Object superObject = null;
                try {
                    superConstructor = c.getSuperclass().getConstructor();
                    if(superConstructor != null){
                        superObject = superConstructor.newInstance();
                    }
                } catch (NoSuchMethodException |
                        InstantiationException |
                        IllegalAccessException |
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(superObject != null){
                    size += getSizeOfSuperObject(superObject, ++deep);
                }
            }
        }
        Field[] fields = c.getDeclaredFields();
        for(Field f : fields){
            if(Modifier.isStatic(f.getModifiers())) continue;
            f.setAccessible(true);
            Object value;
            try {
                value = f.get(o);
                size += objectSize.getSizeOfObject(value, deep);
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        long mod = size % 8;
        String adding = "0 forFactor8";
        if(mod != 0) {
            size += (8 - mod);
            adding = util.printAdding(8-mod, "super");
        }
        System.out.println(util.printSize(deep, c, size, "super") + "\t<include " + adding + ">");
        --deep;
        return size;
    }
}
