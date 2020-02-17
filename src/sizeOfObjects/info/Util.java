package sizeOfObjects.info;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmifed
 */
class Util {

    String printDeep(int deep){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < deep; i++){
            sb.append("\t");
        }
        return sb.toString();
    }
    List<String> deleteStatic(List<String> list){
        List<String> nonStatic = new ArrayList<>();
        for(String s : list){
            if(!s.contains("static")){
                nonStatic.add(s);
            }
        }
        return nonStatic;
    }
    List<String> deleteNoneStatic(List<String> list){
        List<String> staticItems = new ArrayList<>();
        for(String s : list){
            if(s.contains("static")){
                staticItems.add(s);
            }
        }
        return staticItems;
    }
    List<String> infoStatics(List<List<String>> data){
        List<String> fields = new ArrayList<>();
        for(List<String> d : data){
            fields.addAll(deleteNoneStatic(d));
        }
        return fields;
    }
    List<String> info(String itemInfo, List<List<String>> data){
        List<String> list = new ArrayList<>();

        List<String> items = infoStatics(data);
        list.add("STATIC " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");

        List<String> publicItems = data.get(0);
        List<String> defaultItems = data.get(1);
        List<String> protectedItems = data.get(2);
        List<String> privateItems = data.get(3);

        items = deleteStatic(publicItems);
        list.add("PUBLIC " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");

        items = deleteStatic(defaultItems);
        list.add("DEFAULT " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");

        items = deleteStatic(protectedItems);
        list.add("PROTECTED " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");

        items = deleteStatic(privateItems);
        list.add("PRIVATE " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");


        return list;
    }
    List<String> infoNonDeprecated(String itemInfo, List<List<String>> data){
        List<String> list = new ArrayList<>();

        List<String> items = infoStatics(data);
        items.removeIf(s -> s.contains("Deprecated"));
        list.add("STATIC " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");

        List<String> publicItems = data.get(0);
        List<String> defaultItems = data.get(1);
        List<String> protectedItems = data.get(2);
        List<String> privateItems = data.get(3);

        items = deleteStatic(publicItems);
        items.removeIf(s -> s.contains("Deprecated"));
        list.add("PUBLIC " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");

        items = deleteStatic(defaultItems);
        items.removeIf(s -> s.contains("Deprecated"));
        list.add("DEFAULT " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");

        items = deleteStatic(protectedItems);
        items.removeIf(s -> s.contains("Deprecated"));
        list.add("PROTECTED " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");

        items = deleteStatic(privateItems);
        items.removeIf(s -> s.contains("Deprecated"));
        list.add("PRIVATE " +  itemInfo + ": " + items.size());
        list.addAll(items);
        list.add("");


        return list;
    }

    void print(List<String> list){
        for(String s : list){
            System.out.println(s);
        }
    }
    void print(String s){        System.out.println(s);    }
}
