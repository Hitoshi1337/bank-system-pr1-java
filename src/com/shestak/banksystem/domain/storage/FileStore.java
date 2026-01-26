package com.shestak.banksystem.domain.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStore {
  private final Path dir;

  public FileStore(String folder) {
    this.dir = Path.of(folder);
    try {
      Files.createDirectories(dir);
    } catch (IOException e) {
      throw new RuntimeException("Cannot create storage folder: " + folder, e);
    }
  }

  public void save(String name, Object data) {
    Path file = dir.resolve(name + ".bin");
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
      out.writeObject(data);
    } catch (IOException e) {
      throw new RuntimeException("Save failed: " + name, e);
    }
  }

  public <T> T load(String name, Class<T> type) {
    Path file = dir.resolve(name + ".bin");
    if (!Files.exists(file)) return null;

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.toFile()))) {
      Object obj = in.readObject();
      return type.cast(obj);
    } catch (Exception e) {
      // если файл битый — удаляем и продолжаем
      try { Files.deleteIfExists(file); } catch (IOException ignored) {}
      throw new RuntimeException("Load failed: " + name + " (file removed)", e);
    }
  }
}
