package sizeOfObjects.info;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmifed
 */
class MethodsInfo {

    private Method[] publicMethods;
    private Method[] declaredMethods;
    private Util util;
    private List<List<String>> data;

    MethodsInfo(Object o) {
        publicMethods = o.getClass().getMethods();
        declaredMethods = o.getClass().getDeclaredMethods();
        util = new Util();
        data = new ArrayList<>();
        createData();
    }

    List<String> infoMethods(){
        return util.info("METHODS" , data);
    }
    List<String> infoNonDeprecatedMethods(){
        return util.infoNonDeprecated("NOT DEPRECATED METHODS" , data);
    }


    private void createData(){
        data.add(infoPublicMethods());
        data.add(infoDefaultMethods());
        data.add(infoProtectedMethods());
        data.add(infoPrivateMethods());
    }

    private List<String> infoPublicMethods(){
        List<String> methods = new ArrayList<>();
        for(Method m : publicMethods){
            methods.add(getMethodInfo(m));
        }
        return methods;
    }
    private List<String> infoDefaultMethods(){
        List<String> methods = new ArrayList<>();
        for(Method m : declaredMethods){
            int mod = m.getModifiers();
            if(!(Modifier.isProtected(mod) || Modifier.isPrivate(mod) || Modifier.isPublic(mod))){
                methods.add(getMethodInfo(m));
            }
        }
        return methods;
    }
    private List<String> infoProtectedMethods(){
        List<String> methods = new ArrayList<>();
        for(Method m : declaredMethods){
            int mod = m.getModifiers();
            if(Modifier.isProtected(mod)){
                methods.add(getMethodInfo(m));
            }
        }
        return methods;
    }
    private List<String> infoPrivateMethods(){
        List<String> methods = new ArrayList<>();
        for(Method m : declaredMethods){
            int mod = m.getModifiers();
            if(Modifier.isPrivate(mod)){
                methods.add(getMethodInfo(m));
            }
        }
        return methods;
    }
    private String getMethodInfo(Method method){
        method.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        Annotation[] aa = method.getDeclaredAnnotations();
        for(Annotation a : aa){
            sb
                    .append(a.toString())
                    .append(" <type:")
                    .append(a.annotationType()
                            .getSimpleName())
                    .append(">")
                    .append("\n");
        }
        int m = method.getModifiers();


        if((Modifier.isProtected(m) || Modifier.isPrivate(m) || Modifier.isPublic(m))){
            sb.append(Modifier.toString(m))
                    .append(" ");
        }
        sb
            .append(method.getGenericReturnType().getTypeName())
            .append(" ")
            .append(method.getName())
            .append("(");

        Type[] tt = method.getGenericParameterTypes();

        for(Type t : tt){
            sb.append(t.getTypeName()).append(", ");
        }
        if(tt.length != 0){
            sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1).append(")");
        }else {
            sb.append(")");
        }

        Class<?>[] cc = method.getExceptionTypes();
        if(cc.length != 0){
            sb.append(" throws ");
            for(Class<?> c : cc){
                sb.append(c.getSimpleName()).append(", ");
            }
            sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    String getMethodsNameReturnType(String returnType){
        if(data == null || data.size() == 0){
            return "this object has not any methods";
        }
        StringBuilder sb = new StringBuilder();
        for(List<String> list : data){
            for(String name : list){
                switch (returnType){
                    case "boolean":
                        if(name.contains(" boolean ") || name.contains(" Boolean ")  ||
                                name.contains(" boolean[] ") || name.contains(" Boolean[] "))
                            sb.append(name).append("\n");
                        break;
                    case "byte":
                        if(name.contains(" byte ") || name.contains(" Byte ") ||
                                name.contains(" byte[] ") || name.contains(" Byte[] "))
                            sb.append(name).append("\n");
                        break;
                    case "short":
                        if(name.contains(" short ") || name.contains(" Short ") ||
                                name.contains(" short[] ") || name.contains(" Short[] "))
                            sb.append(name).append("\n");
                        break;
                    case "char":
                        if(name.contains(" char ") || name.contains(" Character ") ||
                                name.contains(" char[] ") || name.contains(" Character[] "))
                            sb.append(name).append("\n");
                        break;
                    case "int":
                        if(name.contains(" int ") || name.contains(" Integer ") ||
                                name.contains(" int[] ") || name.contains(" Integer[] "))
                            sb.append(name).append("\n");
                        break;
                    case "float":
                        if(name.contains(" float ") || name.contains(" Float ") ||
                                name.contains(" float[] ") || name.contains(" Float[] "))
                            sb.append(name).append("\n");
                        break;
                    case "long":
                        if(name.contains(" long ") || name.contains(" Long ") ||
                                name.contains(" long[] ") || name.contains(" Long[] "))
                            sb.append(name).append("\n");
                        break;
                    case "double":
                        if(name.contains(" double ") || name.contains(" Double ") ||
                                name.contains(" double[] ") || name.contains(" Double[] "))
                            sb.append(name).append("\n");
                        break;
                    case "boolean[]":
                        if(name.contains(" boolean[] ") || name.contains(" Boolean[] "))
                            sb.append(name).append("\n");
                        break;
                    case "byte[]":
                        if(name.contains(" byte[] ") || name.contains(" Byte[] "))
                            sb.append(name).append("\n");
                        break;
                    case "short[]":
                        if(name.contains(" short[] ") || name.contains(" Short[] "))
                            sb.append(name).append("\n");
                        break;
                    case "char[]":
                        if(name.contains(" char[] ") || name.contains(" Character[] "))
                            sb.append(name).append("\n");
                        break;
                    case "int[]":
                        if(name.contains(" int[] ") || name.contains(" Integer[] "))
                            sb.append(name).append("\n");
                        break;
                    case "float[]":
                        if(name.contains(" float[] ") || name.contains(" Float[] "))
                            sb.append(name).append("\n");
                        break;
                    case "long[]":
                        if(name.contains(" long[] ") || name.contains(" Long[] "))
                            sb.append(name).append("\n");
                        break;
                    case "double[]":
                        if(name.contains(" double[] ") || name.contains(" Double[] "))
                            sb.append(name).append("\n");
                        break;
                    default:
                        if(name.contains("." + returnType + " ") ||
                            name.contains("." + returnType + "<") ||
                            name.contains("." + returnType + "[") ||
                                name.contains(" " + returnType + " "))
                            sb.append(name).append("\n");
                }
            }
        }
        String result = sb.toString().trim();
        if(result.equals("")){
            return "No such methods with return type " + returnType;
        }else {
            return result;
        }
    }


}
