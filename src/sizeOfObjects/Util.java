package sizeOfObjects;

import java.lang.reflect.Field;
/**
 * @author dmifed
 */
class Util {
    String printSize(int deep, Class<?> c, long size, String type, Field field){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < deep-1; i++){
            sb.append("\t");
        }
        sb
            .append(size)
            .append("\t<object::")
            .append(c.getSimpleName())
            .append(">\t<name::")
            .append(field.getName())
            .append(">\t<type::")
            .append(type)
            .append(">");
        return sb.toString();
    }
    String printSize(int deep, Class<?> c, long size, String type){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < deep-1; i++){
            sb.append("\t");
        }
        sb
            .append(size)
            .append("\t<object::")
            .append(c.getSimpleName())
            .append(">\t<type::")
            .append(type)
            .append(">");
        return sb.toString();
    }
    String printSize(int deep, long size, String type){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < deep; i++){
            sb.append("\t");
        }
        sb
            .append(size)
            .append("\t<primitive type::")
            .append(type)
            .append(">\t<name::null>");
        return  sb.toString();
    }

    String printNull(int deep, String type, Field f){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < deep; i++){
            sb.append("\t");
        }
        sb
            .append("null")
            .append("\t<type::")
            .append(type)
            .append(">")
            .append("\t<name::null>");
        return  sb.toString();
    }

    String printAdding(long size, String type){
        return size + " forFactor8";
    }

    String printNull (int deep){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < deep; i++){
            sb.append("\t");
        }
        return  sb.toString();
    }
}
