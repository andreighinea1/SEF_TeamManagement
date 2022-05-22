//package com.work.teammanagement.model.databases;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Paths;
//
// TODO: Implement this class properly, as it doesn't work like this apparently
//public class JsonLoaderUnloader<T> {
//    private T data;
//    private final String dbName;
//    private final ObjectMapper objectMapper;
//
//    protected JsonLoaderUnloader(String dbName, T data) {
//        this.data = data;
//        this.dbName = dbName;
//        objectMapper = new ObjectMapper();
//    }
//
//    public T loadDB() throws FileNotFoundException {
//        System.out.println();
//        try {
//            data = objectMapper.readValue(Paths.get(dbName).toFile(), new TypeReference<>() {
//            });
//            System.out.println(data);
//            System.out.println(data.getClass().getName());
//            return data;
//        } catch (FileNotFoundException e) {
//            throw e;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void saveDB() {
//        System.out.printf("Saving DB for '%s'%n", dbName);
//        try {
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(dbName), data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//    }
//}
