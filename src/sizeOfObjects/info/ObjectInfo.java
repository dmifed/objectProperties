package sizeOfObjects.info;

/**
 * @author dmifed
 */


import java.util.List;
import static sizeOfObjects.info.Util.*;

/**
 * Print
 *      (modifiers) fields              +
 *      (modifiers) methods (param)     +
 *      (modifiers) constructors (param) +
 *      tree of superclasses +
 *      annotations +
 *      trees interfaces +
 *      list of methods with specify return type +
 */
public class ObjectInfo {

    private FieldsInfo f;
    private MethodsInfo m;
    private ConstructorsInfo c;
    private SuperclassesInfo sup;
    private InterfacesInfo inter;
    private AnnotationsInfo a;
    private Util util;


    public void getInfo(Object o){
        if(o == null){
            util.print("object is null");
        }else {
            List<String> list = createInfo(o);
            util.print(list);
        }

    }
    public void getInfo(boolean i){
        util.print("boolean = " + i);
    }
    public void getInfo(byte i){
        util.print("byte = " + i);
    }
    public void getInfo(short i){
        util.print("short = " + i);
    }
    public void getInfo(char i){
        util.print("char = " + i);
    }
    public void getInfo(int i){
        util.print("int = " + i);
    }
    public void getInfo(long i){
        util.print("long = " + i);
    }
    public void getInfo(float i){
        util.print("float = " + i);
    }
    public void getInfo(double i){
        util.print("double = " + i);
    }

    public void getNonDeprecatedInfo(Object o){
        if(o == null){
            util.print("object is null");
        }else {
            List<String> nonDeprecated = createNonDeprecatedInfo(o);
            //nonDeprecated.removeIf(s -> s.contains("Deprecated"));
            util.print(nonDeprecated);
        }

    }
    public void getNonDeprecatedInfo(boolean i){
        util.print("boolean = " + i);
    }
    public void getNonDeprecatedInfo(byte i){
        util.print("byte = " + i);
    }
    public void getNonDeprecatedInfo(short i){
        util.print("short = " + i);
    }
    public void getNonDeprecatedInfo(char i){
        util.print("char = " + i);
    }
    public void getNonDeprecatedInfo(int i){
        util.print("int = " + i);
    }
    public void getNonDeprecatedInfo(long i){
        util.print("long = " + i);
    }
    public void getNonDeprecatedInfo(float i){
        util.print("float = " + i);
    }
    public void getNonDeprecatedInfo(double i){
        util.print("double = " + i);
    }

    public String getMethodsNameReturnType(String returnType){
        if(m == null){
            return "this object has not any methods";
        }
        return m.getMethodsNameReturnType(returnType);
    }

    private void init(Object o){
        util = new Util();
        f = new FieldsInfo(o);
        m = new MethodsInfo(o);
        c = new ConstructorsInfo(o);
        sup = new SuperclassesInfo(o);
        inter = new InterfacesInfo(o);
        a = new AnnotationsInfo(o);



    }
    private List<String> createInfo(Object o){
        init(o);
        List<String> list = f.infoFields();
        list.addAll(m.infoMethods());
        list.addAll(c.infoConstructors());
        list.addAll(sup.getSuperclasses());
        for(List<String> l : inter.getInterfaces()){            list.addAll(l);        }
        list.addAll(a.getAnnotations());
        return list;
    }
    private List<String> createNonDeprecatedInfo(Object o){
        init(o);
        List<String> list = f.infoNonDeprecatedFields();
        list.addAll(m.infoNonDeprecatedMethods());
        list.addAll(c.infoNonDeprecatedConstructors());
        list.addAll(sup.getSuperclasses());
        for(List<String> l : inter.getInterfaces()){            list.addAll(l);        }
        return list;
    }


}
