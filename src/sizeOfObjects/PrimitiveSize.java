package sizeOfObjects;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
/**
 * @author dmifed
 */
class PrimitiveSize {
    private Util util;

    void init(Util util) {
        this.util = util;
    }

    long getSizeOfPrimitive(Field f, int deep){
        ++deep;
        String type = f.getType().toString();
        if(Modifier.isStatic(f.getModifiers())) return 0;
        StringBuilder sb = new StringBuilder();
        int size = 0;
        if(type.equals("byte") || type.equals("boolean")){
            size = 1;
        }
        if(type.equals("short") || type.equals("char")){
            size = 2;
        }
        if(type.equals("int") || type.equals("float")){
            size = 4;
        }
        if(type.equals("long") || type.equals("double")){
            size = 8;
        }
        for(int i = 0; i < deep; i++){
            sb.append("\t");
        }
        sb
            .append(size)
            .append("\t<primitive type::")
            .append(type)
            .append(">\t<name::")
            .append(f.getName())
            .append(">");
        System.out.println(sb.toString());
        --deep;
        return size;
    }

    long getSizeOfPrimitive(Object o, int deep){
        ++deep;
        String type = o.getClass().getTypeName();
        StringBuilder sb = new StringBuilder();
        int size = 0;
        if(type.equals("byte") || type.equals("boolean")){
            size = 1;
        }
        if(type.equals("short") || type.equals("char")){
            size = 2;
        }
        if(type.equals("int") || type.equals("float")){
            size = 4;
        }
        if(type.equals("long") || type.equals("double")){
            size = 8;
        }
        for(int i = 0; i < deep; i++){
            sb.append("\t");
        }
        sb
                .append(size)
                .append("\t<primitive type::")
                .append(type)
                .append(">\t<name::null>");
        System.out.println(sb.toString());
        --deep;
        return size;
    }

    long getSizeOfPrimitive(byte f, int deep){
        int size = 1;
        System.out.println(util.printSize(deep+1, size, "byte"));
        return size;
    }
    long getSizeOfPrimitive(boolean f, int deep){
        int size = 1;
        System.out.println(util.printSize(deep+1, size, "boolean"));
        return size;
    }
    long getSizeOfPrimitive(char f, int deep){
        int size = 2;
        System.out.println(util.printSize(deep+1, size, "char"));
        return size;
    }
    long getSizeOfPrimitive(short f, int deep){
        int size = 2;
        System.out.println(util.printSize(deep+1, size, "short"));
        return size;
    }
    long getSizeOfPrimitive(int f, int deep){
        int size = 4;
        System.out.println(util.printSize(deep+1, size, "int"));
        return size;
    }
    long getSizeOfPrimitive(float f, int deep){
        int size = 4;
        System.out.println(util.printSize(deep+1, size, "float"));
        return size;
    }
    long getSizeOfPrimitive(long f, int deep){
        int size = 8;
        System.out.println(util.printSize(deep+1, size, "long"));
        return size;
    }
    long getSizeOfPrimitive(double f, int deep){
        int size = 8;
        System.out.println(util.printSize(deep+1, size, "double"));
        return size;
    }
}
