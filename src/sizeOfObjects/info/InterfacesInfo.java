package sizeOfObjects.info;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmifed
 */
class InterfacesInfo {
    private List<List<String>> allInterfaces;
    private Object o;
    private Util util;
    InterfacesInfo(Object o) {
        this.o = o;
        allInterfaces = new ArrayList<>();
        util = new Util();
    }

    List<List<String>> getInterfaces(){
        allHierarchy();
        List<String> header = new ArrayList<>();
        header.add("INTERFACES: " + allInterfaces.size());
        allInterfaces.add(0, header);
        List<String> footer = new ArrayList<>();
        footer.add("");
        allInterfaces.add(footer);
        return allInterfaces;
    }

    private void allHierarchy(){
        Class<?>[] interfaces = o.getClass().getInterfaces();
        for(Class<?> i : interfaces){
            List<String> hierarchy = new ArrayList<>();
            allInterfaces.add(getHierarchyInterface(i, 0, hierarchy));
        }
    }

    private List<String> getHierarchyInterface(Class<?> inter, int deep, List<String> hierarchy){
        String name = inter.getName();
        String space = util.printDeep(deep);
        Annotation[] annotations = inter.getAnnotations();
        StringBuilder sb = new StringBuilder();
        for (Annotation a : annotations){
            sb.append(space).append(a.toString()).append("\n");
        }
        sb.append(space).append(name);
        hierarchy.add(sb.toString());
        Class<?>[] inters = inter.getInterfaces();
        for (Class<?> i : inters){
            getHierarchyInterface(i, ++deep, hierarchy);
        }
        return hierarchy;
    }


}
