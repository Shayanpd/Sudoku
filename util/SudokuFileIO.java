package util;

import model.SudokuModel;
import java.io.*;

/** Utility class providing methods to serialize and deserialize a {@code SudokuModel}. */
public class SudokuFileIO {
  /**
   * Serialize the provided {@code SudokuModel} to the specified file.
   *
   * @param file the file to which the {@code SudokuModel} should be serialized
   * @param data the {@code SudokuModel} to be serialized
   * @throws IOException if an I/O error occurs during serialization
   */
  public static void serializeToFile(File file, SudokuModel data) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); ) {
      objectOutputStream.writeObject(data);
    } catch (IOException e) {
      System.out.println("IOException in SudokuFileIO.serializeToFile");
      System.out.println(e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Deserialize and return the {@code SudokuModel} from the specified file.
   *
   * @param file the file from which the {@code SudokuModel} should be deserialized
   * @return the deserialized {@code SudokuModel}
   * @throws IOException if an I/O error occurs during deserialization
   * @throws ClassNotFoundException if the class of a serialized object cannot be found
   */
  public static SudokuModel deSerializeFromFile(File file) {
    try (FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); ) {
      SudokuModel model = (SudokuModel) objectInputStream.readObject();
      return model;
    } catch (IOException e) {
      System.out.println("IOException in SudokuFileIO.deSerializeFromFile");
      System.out.println(e);
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException in SudokuFileIO.deSerializeFromFile");
      System.out.println(e);
      throw new RuntimeException(e);
    }
  }

  private SudokuFileIO() {}
}
