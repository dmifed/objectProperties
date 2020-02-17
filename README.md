*Calculate size of an object, print object info, find list methods of object with specify return type.
Object should be a primitive type or a reference type or an array

*EXAMPLES

*Get size of object

*CalculationSizeOfObject cso = new CalculationSizeOfObject();
*long size = cso.getSize(youObject)

*In really JVM size will be smaller because of optimizations.
*This calculator maybe a upper estimation size of object.
*Count:
  *size of fields (reference type and primitive type);
  *size of references;
  *size of inner superclass object;
  *size of header object

*View object info

*ObjectInfo oI = new ObjectInfo();
*oI.getInfo(youObject)

*View methods of object with specify return type

*ObjectInfo oI = new ObjectInfo();
*oI.getMethodsNameReturnType("int")

