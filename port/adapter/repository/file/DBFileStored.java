package port.adapter.repository.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBFileStored<T> {
    public List<T> load(String filename) throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileOutputStream = new FileInputStream(filename);
            ObjectInputStream objectOutputStream = new ObjectInputStream(fileOutputStream);
            if (objectOutputStream != null) {
                T temp = (T) objectOutputStream.readObject();
                boolean endOfFile = false;
                List<T> listIn = new ArrayList<>();
                while (endOfFile != true) {
                    try {
                        listIn.add(temp);
                        temp = (T) objectOutputStream.readObject();
                    } catch (EOFException e) {
                        endOfFile = true;
                    }
                }
                objectOutputStream.close();
                return listIn;
            }
            return new ArrayList<>();
        } catch (Exception e) {

        }
        return new ArrayList<>();

    }

    public void save(List<T> listItem, String filename) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        listItem.forEach(t -> {
            try {
                objectOutputStream.writeObject(t);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
