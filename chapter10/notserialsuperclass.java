// Serialized a serializable class with notSerializable superclass,
// then deserialized it. The superclass fields are not serialized,
// and instead called the no-argument Constructor

import java.io.*;

class notserializable {
    public int var1;
    transient Integer var1_1 = 2;
    public notserializable() {
        var1 = 10; // this no-argument constructor is called
        // when deserializing the object
    }
}

class serializableSubclass extends notserializable implements Serializable {
    public int var2;
    public serializableSubclass() {
        var2 = 20;
    }
}

@SuppressWarnings("unused")
public class notserialsuperclass {
    public static void main(String[] args) {
        if (false) {
            serializableSubclass a = new serializableSubclass();
            a.var1_1 = 12; // modify the transient variable
            a.var1 = 11;
            a.var2 = 21;

            try {
                FileOutputStream fos = new FileOutputStream("test.file");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(a);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            serializableSubclass b = null;
            try {
                FileInputStream fis = new FileInputStream("test.file");
                ObjectInputStream ois = new ObjectInputStream(fis);
                b = (serializableSubclass) ois.readObject();
                ois.close();
                System.out.println(b.var1_1);
                System.out.println(b.var1);
                System.out.println(b.var2);
                // output 2,10,21
                // 2: transient variable default value, not serialized
                // 10: no-argument constructor
                // 21: var2 is serialized
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
