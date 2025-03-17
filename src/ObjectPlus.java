import java.io.*;
import java.util.*;

public class ObjectPlus implements Serializable {
    private static Map<Class, List> extent = new HashMap<>();
    public static final String NAZWA_PLIKU = "extent.ser";

    public ObjectPlus() {
        addToExtent();
    }

    public static void saveExtent() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NAZWA_PLIKU))){
            oos.writeObject(extent);
        }
    }

    public static void loadExtent() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NAZWA_PLIKU))){
            extent = (Map<Class, List>) ois.readObject();
        }
    }


    public static <T> List<T> getExtentFromClass(Class<T> c) {
        extent.computeIfAbsent(c, _ -> new ArrayList<>());
        return Collections.unmodifiableList(extent.get(c));
    }

    protected void addToExtent(){
        List list = extent.computeIfAbsent(this.getClass(), _ -> new ArrayList<>());
        list.add(this);
    }

    protected void removeFromExtent(){
        List list = extent.get(this.getClass());
        if(list != null){
            list.remove(this);
        }
    }



}
