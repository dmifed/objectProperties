package sizeOfObjects.info;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmifed
 */
class ConstructorsInfo {
    private Constructor<?>[] publicConstructors;
    private Constructor<?>[] declaredConstructors;
    private Util util;
    private List<List<String>> data;

    ConstructorsInfo(Object o) {
        publicConstructors = o.getClass().getConstructors();
        declaredConstructors = o.getClass().getDeclaredConstructors();
        util = new Util();
        data = new ArrayList<>();
        createData();
    }

    private void createData(){
        data.add(infoPublicConstructors());
        data.add(infoDefaultConstructors());
        data.add(infoProtectedConstructors());
        data.add(infoPrivateConstructors());
    }

    List<String> infoConstructors(){
        return util.info("CONSTRUCTORS" , data);
    }

    List<String> infoNonDeprecatedConstructors(){
        return util.infoNonDeprecated("NOT DEPRECATED CONSTRUCTORS" , data);
    }

    private List<String> infoPublicConstructors(){
        List<String> constructors = new ArrayList<>();
        for(Constructor<?> m : publicConstructors){
            constructors.add(getConstructorInfo(m));
        }
        return constructors;
    }

    private List<String> infoDefaultConstructors(){
        List<String> constructors = new ArrayList<>();
        for(Constructor<?> m : declaredConstructors){
            int mod = m.getModifiers();
            if(!(Modifier.isProtected(mod) || Modifier.isPrivate(mod) || Modifier.isPublic(mod))){
                constructors.add(getConstructorInfo(m));
            }
        }
        return constructors;
    }

    private List<String> infoProtectedConstructors(){
        List<String> constructors = new ArrayList<>();
        for(Constructor<?> m : declaredConstructors){
            int mod = m.getModifiers();
            if(Modifier.isProtected(mod)){
                constructors.add(getConstructorInfo(m));
            }
        }
        return constructors;
    }

    private List<String> infoPrivateConstructors(){
        List<String> constructors = new ArrayList<>();
        for(Constructor<?> m : declaredConstructors){
            int mod = m.getModifiers();
            if(Modifier.isPrivate(mod)){
                constructors.add(getConstructorInfo(m));
            }
        }
        return constructors;
    }

    private String getConstructorInfo(Constructor<?> constr){
        constr.setAccessible(true);
        StringBuilder sb = new StringBuilder();
        Annotation[] aa = constr.getDeclaredAnnotations();
        for(Annotation a : aa){
            sb
                    .append(a.toString())
                    .append(" <type:")
                    .append(a.annotationType()
                            .getSimpleName())
                    .append(">")
                    .append("\n");
        }
        int m = constr.getModifiers();


        if((Modifier.isProtected(m) || Modifier.isPrivate(m) || Modifier.isPublic(m))){
            sb.append(Modifier.toString(m))
                    .append(" ");
        }
        sb
                .append(constr.getName())
                .append("(");

        Type[] tt = constr.getGenericParameterTypes();

        for(Type t : tt){
            sb.append(t.getTypeName()).append(", ");
        }
        if(tt.length != 0){
            sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1).append(")");
        }else {
            sb.append(")");
        }

        Class<?>[] cc = constr.getExceptionTypes();
        if(cc.length != 0){
            sb.append(" throws ");
            for(Class<?> c : cc){
                sb.append(c.getSimpleName()).append(", ");
            }
            sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }


}
