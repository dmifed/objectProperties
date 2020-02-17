package sizeOfObjects;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dmifed
 */
class ArraySize {

    private Util util;
    private PrimitiveSize primitiveSize;
    private ObjectSize objectSize;

    void init(Util util, PrimitiveSize primitiveSize, ObjectSize objectSize) {
        this.util = util;
        this.primitiveSize = primitiveSize;
        this.objectSize = objectSize;
    }

    long getSizeOfArray(Field f, Object o, int deep){

        // f - array type
        // o - object contains array
        if(Modifier.isStatic(f.getModifiers())) return 0;
        ++deep;
        long fieldSize = 0;
        fieldSize += 8; //head array
        fieldSize += 4; //length array
        f.setAccessible(true);
        Class<?> oC = f.getType(); //class of array
        int len = 0;
        Object o2 = null;
        try {
            o2 = f.get(o); // array
            if(o2 == null){                return 0;            }
            len = Array.getLength(o2);
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        for(int i = 0; i < len; i++){
            Object value = Array.get(o2, i);
            Pattern pattern = Pattern.compile("\\[.{1}$");
            Matcher m = pattern.matcher(o2.getClass().getName());
            if(m.find()){ // primitive
                fieldSize += sizePrimitiveArrayItem(value, deep);
            }else {
                fieldSize += objectSize.getSizeOfObject(value, deep);
            }
        }

        long mod = fieldSize % 8;
        String adding = "0 forFactor8";
        if(mod != 0) {
            fieldSize += (8 - mod);
            adding = util.printAdding(8-mod, "arrayField");
        }
        System.out.println(util.printSize(deep, oC, fieldSize, "arrayField", f) + "\t<include " + adding + " and header = 12 (8+4)>");
        --deep;
        return fieldSize;
    }

    long getSizeOfArray(Object o){
        if(o == null){            return 0;        }
        // o - array
        int deep = 1;
        long fieldSize = 0;
        fieldSize += 8; //head array
        fieldSize += 4; //length array
        Class<?> oC = o.getClass(); //class of array
        int len = Array.getLength(o);
        for(int i = 0; i < len; i++){
            Object value = Array.get(o, i);
            Pattern pattern = Pattern.compile("\\[.{1}$");
            Matcher m = pattern.matcher(oC.getName());
            if(m.find()){ // primitive
                fieldSize += sizePrimitiveArrayItem(value, deep);
            }else {
                fieldSize += objectSize.getSizeOfObject(value, deep);
            }
        }
        long mod = fieldSize % 8;
        String adding = "0 forFactor8";
        if(mod != 0) {
            fieldSize += (8 - mod);
            adding = util.printAdding(8-mod, "arrayField");
        }
        System.out.println(util.printSize(deep, oC, fieldSize, "arrayField") + "\t<include " + adding + " and header = 12 (8+4)>");
        --deep;
        return fieldSize;
    }

    private long sizePrimitiveArrayItem(Object value, int deep){
        long size = 0;
        if(value instanceof Byte){
            Byte character = (Byte) value;
            size = primitiveSize.getSizeOfPrimitive(character.byteValue(), deep);
        }
        else if(value instanceof Boolean){
            Boolean character = (Boolean) value;
            size = primitiveSize.getSizeOfPrimitive(character.booleanValue(), deep);
        }
        else if(value instanceof Character){
            Character character = (Character) value;
            size = primitiveSize.getSizeOfPrimitive(character.charValue(), deep);
        }
        else if(value instanceof Short){
            Short character = (Short) value;
            size = primitiveSize.getSizeOfPrimitive(character.shortValue(), deep);
        }
        else if(value instanceof Integer){
            Integer character = (Integer) value;
            size = primitiveSize.getSizeOfPrimitive(character.intValue(), deep);
        }
        else if(value instanceof Float){
            Float character = (Float) value;
            size = primitiveSize.getSizeOfPrimitive(character.floatValue(), deep);
        }
        else if(value instanceof Long){
            Long character = (Long) value;
            size = primitiveSize.getSizeOfPrimitive(character.longValue(), deep);
        }
        else if(value instanceof Double){
            Double character = (Double) value;
            size = primitiveSize.getSizeOfPrimitive(character.doubleValue(), deep);
        }
        return size;
    }


}
