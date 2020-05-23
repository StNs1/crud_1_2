package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    private static final String PATH_TO_PROPERTIES = "C:\\Users\\velik\\IdeaProjects\\crud_1_2\\src\\main\\resources\\dao.properties";
    public static String getUserDao() {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            String daoType = properties.getProperty("daoType");
            return daoType;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
