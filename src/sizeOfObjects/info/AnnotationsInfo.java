package sizeOfObjects.info;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmifed
 */
class AnnotationsInfo {

    private List<List<String>> allAnnotations;
    private Object o;
    private Util util;
    AnnotationsInfo(Object o) {
        this.o = o;
        allAnnotations = new ArrayList<>();
        util = new Util();
    }


    List<String> getAnnotations(){
        List<String> hierarchy = new ArrayList<>();
        Annotation[] annotations = o.getClass().getDeclaredAnnotations();
        hierarchy.add("ANNOTATIONS: " + annotations.length);
        StringBuilder sb;
        for (Annotation a : annotations){
            sb = new StringBuilder();
            Annotation[] annotAnnotations = a.annotationType().getDeclaredAnnotations();
            for(Annotation annot : annotAnnotations){
                sb.append(util.printDeep(1)).append(annot.toString()).append("\n");
            }
            sb.append(a.toString()).append("\n");
            hierarchy.add(sb.toString());

        }
        hierarchy.add("");
        return hierarchy;
    }
}
