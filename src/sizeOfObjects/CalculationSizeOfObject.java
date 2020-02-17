package sizeOfObjects;

/**
 * @author dmifed
 * Calculate size of an object.
 * In really JVM size will be smaller because of optimizations.
 * This calculator maybe a upper estimation size of object.
 * Count:
 *      size of fields (reference type and primitive type);
 *      size of references;
 *      size of inner superclass object;
 *      size of header object/
 *
 * Calculator takes:
 *      objects;
 *      arrays;
 *      primitive/
 *
 * How to use
 *      CalculationSizeOfObject calculationSizeOfObject = new CalculationSizeOfObject();
 *      List<Integer> list = new ArrayList<>();
 *      ...
 *      long size = calculationSizeOfObject.getSize(list);
 */

public class CalculationSizeOfObject {

    private Util util;
    private ObjectSize objectSize;
    private PrimitiveSize primitiveSize;
    private ArraySize arraySize;
    private SuperObjectSize superObjectSize;

    public CalculationSizeOfObject() {
        this.util = new Util();
        this.objectSize = new ObjectSize();
        this.primitiveSize = new PrimitiveSize();
        this.arraySize = new ArraySize();
        this.superObjectSize = new SuperObjectSize();
        init();
    }

    private void init(){
        primitiveSize.init(util);
        arraySize.init(util, primitiveSize, objectSize);
        superObjectSize.init(util, objectSize);
        objectSize.init(util, superObjectSize, arraySize, primitiveSize);
    }



    public long getSize(Object o){
        if(o == null){
            System.out.println("Starting Null: ");
            return 0;
        }
        Class<?> c = o.getClass();
        System.out.println("Starting Object: " + c.getSimpleName());
        if(c.isArray()){
            System.out.println("Starting Array: " + c.getSimpleName());
            return arraySize.getSizeOfArray(o);
        }
        return objectSize.getSizeOfObject(o, 0);
    }

    public long getSize(byte b){
        System.out.println("Starting byte: ");
        return 1;
    }
    public long getSize(boolean b){
        System.out.println("Starting boolean: ");
        return 1;
    }
    public long getSize(short b){
        System.out.println("Starting short: ");
        return 2;
    }
    public long getSize(char b){
        System.out.println("Starting char: ");
        return 2;
    }
    public long getSize(int b){
        System.out.println("Starting int");
        return 4;
    }
    public long getSize(float b){
        System.out.println("Starting float: ");
        return 4;
    }
    public long getSize(long b){
        System.out.println("Starting long: ");
        return 8;
    }
    public long getSize(double b){
        System.out.println("Starting double: ");
        return 8;
    }
}
