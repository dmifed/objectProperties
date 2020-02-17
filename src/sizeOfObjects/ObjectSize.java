package sizeOfObjects;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author dmifed
 */
class ObjectSize {

    private Util util;
    private SuperObjectSize superObjectSize;
    private ArraySize arraySize;
    private PrimitiveSize primitiveSize;

    void init(Util util, SuperObjectSize superObjectSize, ArraySize arraySize, PrimitiveSize primitiveSize) {
        this.util = util;
        this.superObjectSize = superObjectSize;
        this.arraySize = arraySize;
        this.primitiveSize = primitiveSize;
    }

    long getSizeOfObject(Object o, int deep){
        if(o == null){            return 0;        }
        if(Modifier.isStatic(o.getClass().getModifiers())) return 0;
        ++deep;
        long size = 0;
        size += 8; // head
        Class<?> c = o.getClass();
        if(c.getSuperclass() != null){
            size += superObjectSize.getSizeOfSuperObject(o, deep);
        }
        Field[] fields = c.getDeclaredFields();
        for(Field f : fields){
            if(Modifier.isStatic(f.getModifiers())) continue;
            f.setAccessible(true);
            if(f.getType().isPrimitive()){
                size += primitiveSize.getSizeOfPrimitive(f, deep);
            }
            if(f.getType().isArray()){
                size += 4; // reference
                System.out.println(util.printSize(deep, 4, "refArray"));
                size += arraySize.getSizeOfArray(f, o, deep);
            }
            //class
            if(f.getType().toString().startsWith("class") && !f.getType().toString().startsWith("class [")){
                size += 4; //reference
                System.out.println(util.printSize(deep, 4, "refObject"));
                Object value;
                try {
                    f.setAccessible(true);
                    value = f.get(o);
                    size += getSizeOfObject(value, deep);
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        long mod = size % 8;
        String adding = "0 forFactor8";
        if(mod != 0) {
            size += (8 - mod);
            adding = util.printAdding(8-mod, "referenceField");
        }
        System.out.println(util.printSize(deep, c, size, "referenceField") + "\t<include " + adding + " and header = 8>");

        --deep;
        return size;
    }

    long getSizeOFObject(boolean i, int deep){
        return 1;
    }
    long getSizeOFObject(byte i, int deep){
        return 1;
    }
    long getSizeOFObject(short i, int deep){
        return 2;
    }
    long getSizeOFObject(char i, int deep){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < deep; j++){
            sb.append("    ");
        }
        sb.append("+").append(2).append("    primitive is char");
        System.out.println(sb.toString());
        --deep;
        return 2;
    }
    long getSizeOFObject(int i, int deep){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < deep; j++){
            sb.append("    ");
        }
        sb.append("+").append(4).append("    primitive is int");
        System.out.println(sb.toString());
        --deep;
        return 4;
    }
    long getSizeOFObject(float i, int deep){
        return 4;
    }
    long getSizeOFObject(long i, int deep){
        return 8;
    }
    long getSizeOFObject(double i, int deep){
        return 8;
    }
}
