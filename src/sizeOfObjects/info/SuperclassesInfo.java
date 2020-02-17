package sizeOfObjects.info;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmifed
 */
class SuperclassesInfo {
    private List<String> superclasses;
    private Object o;
    private Util util;

    SuperclassesInfo(Object o) {
        this.o = o;
        superclasses = new ArrayList<>();
        util = new Util();
    }

    List<String> getSuperclasses(){
        Class<?> c = o.getClass();
        getSuperclass(c, 0);
        superclasses.add(0, "SUPERCLASS:");
        superclasses.add("");
        return superclasses;

    }

    private void getSuperclass(Class<?> c, int deep){
        String isAbstract = "";

        int m = c.getModifiers();
        if(Modifier.isAbstract(m)){
            isAbstract = " <abstract> ";
        }

        superclasses.add(util.printDeep(deep) + isAbstract + c.getName());
        c = c.getSuperclass();
        if(c != null){
            getSuperclass(c, ++deep);
        }
    }

}
