package sizeOfObjects.info;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dmifed
 */
class FieldsInfo {

    private Object o;
    private Field[] publicFields;
    private Field[] declaredFields;
    private Util util;
    private List<List<String>> data;


    FieldsInfo(Object o) {
        this.o = o;
        publicFields = o.getClass().getFields();
        declaredFields = o.getClass().getDeclaredFields();
        util = new Util();
        data = new ArrayList<>();
        createData();
    }

    private void createData(){
        data.add(infoPublicFields());
        data.add(infoDefaultFields());
        data.add(infoProtectedFields());
        data.add(infoPrivateFields());
    }

    List<String> infoFields(){
        return util.info("FIELDS" , data);
    }

    List<String> infoNonDeprecatedFields(){
        return util.infoNonDeprecated("NOT DEPRECATED FIELDS" , data);
    }

    private List<String> infoPublicFields(){
        List<String> fields = new ArrayList<>();
        for(Field f : publicFields){
            fields.add(getFieldInfo(f));
        }
        return fields;
    }

    private List<String> infoDefaultFields(){
        List<String> fields = new ArrayList<>();
        for(Field f : declaredFields){
            int m = f.getModifiers();
            if(!(Modifier.isProtected(m) || Modifier.isPrivate(m) || Modifier.isPublic(m))){
                fields.add(getFieldInfo(f));
            }
        }
        return fields;
    }

    private List<String> infoProtectedFields(){
        List<String> fields = new ArrayList<>();
        for(Field f : declaredFields){
            int m = f.getModifiers();
            if(Modifier.isProtected(m)){
                fields.add(getFieldInfo(f));
            }
        }
        return fields;
    }

    private List<String> infoPrivateFields(){
        List<String> fields = new ArrayList<>();
        for(Field f : declaredFields){
            int m = f.getModifiers();
            if(Modifier.isPrivate(m)){
                fields.add(getFieldInfo(f));
            }
        }
        return fields;
    }

    private String getFieldInfo(Field f){
        f.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        Annotation[] aa = f.getDeclaredAnnotations();
        for(Annotation a : aa){
            sb
                    .append(a.toString())
                    .append(" <type:")
                    .append(a.annotationType()
                            .getSimpleName())
                    .append(">")
                    .append("\n");
        }
        int m = f.getModifiers();


        if((Modifier.isProtected(m) || Modifier.isPrivate(m) || Modifier.isPublic(m))){
            sb.append(Modifier.toString(m))
            .append(" ");
        }
        sb
            .append(f.getType().getSimpleName())
            .append(" ")
            .append(f.getName());

        try {
            sb.append(" = ");
            StringBuilder valueBuilder = new StringBuilder();
            getValue(f, o, valueBuilder);
            sb.append(valueBuilder);



        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void getValue(Field f, Object o, StringBuilder sb) throws IllegalAccessException{
        Object value = f.get(o);
        if(value != null){
            if(f.getType().isPrimitive()){
                String prim;
                prim = primitiveToString(value);
                sb.append(prim);
            } else if(value.getClass().isArray()){
                String arrayClassName = value.getClass().getName();
                Object[] values = new Object[0];
                Pattern pattern = Pattern.compile("\\[.{1}$");
                Matcher m = pattern.matcher(arrayClassName);
                if(m.find()){ // primitive
                    sb.append(primitiveArrayToString(value));
                }else {
                    sb.append(Arrays.deepToString((Object[])value));
                }
            }else{
                sb.append("{");
                Field[] fields = value.getClass().getDeclaredFields();
                for(Field field : fields){
                    field.setAccessible(true);
                    getValue(field, value, sb);
                    sb.append(", ");
                }
                sb.append("}, ");
            }
        }else {
            sb.append("<not initialized>");
        }
    }

    private String primitiveArrayToString(Object value){
        Class<?> itemClass = value.getClass();
        String s = "";
        if(itemClass == boolean[].class){
            boolean[] arr = (boolean[]) value;
            s = Arrays.toString(arr);
        }
        else if(itemClass == byte[].class){
            byte[] arr = (byte[]) value;
            s = Arrays.toString(arr);
        }
        else if(itemClass == short[].class){
            short[] arr = (short[]) value;
            s = Arrays.toString(arr);
        }
        else if(itemClass == char[].class){
            char[] arr = (char[]) value;
            s = Arrays.toString(arr);
        }
        else if(itemClass == int[].class){
            int[] arr = (int[]) value;
            s = Arrays.toString(arr);
        }
        else if(itemClass == float[].class){
            float[] arr = (float[]) value;
            s = Arrays.toString(arr);
        }
        else if(itemClass == long[].class){
            long[] arr = (long[]) value;
            s = Arrays.toString(arr);
        }
        else if(itemClass == double[].class){
            double[] arr = (double[]) value;
            s = Arrays.toString(arr);
        }
        return s;
    }

    private String primitiveToString(Object value){
        Class<?> itemClass = value.getClass();
        String s = "";
        if(itemClass.getName().equals(Boolean.class.getName())){
            boolean a = (Boolean)value;
            s = String.valueOf(a);
        }
        else if(itemClass == Byte.class){
            byte a = (Byte) value;
            s = String.valueOf(a);
        }
        else if(itemClass == Short.class){
            short a = (Short) value;
            s = String.valueOf(a);
        }
        else if(itemClass == Character.class){
            char a = (Character) value;
            s = String.valueOf(a);
        }
        else if(itemClass.getName().equals(Integer.class.getName())){

            int a = (Integer) value;
            s = String.valueOf(a);
        }
        else if(itemClass == Float.class){
            float a = (Float) value;
            s = String.valueOf(a);
        }
        else if(itemClass == Long.class){
            long a = (Long) value;
           s = String.valueOf(a);
        }
        else if(itemClass == Double.class){
            double a = (Double) value;
            s = String.valueOf(a);
        }
        return s;
    }


}
