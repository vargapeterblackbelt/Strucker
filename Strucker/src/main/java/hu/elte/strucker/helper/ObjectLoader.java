package hu.elte.strucker.helper;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ObjectLoader {
    public static Object deserialize(String path, Class clazz) {
        Object object = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enableDefaultTyping();
            object = mapper.readValue(new File(path), clazz);
        } catch (IOException e) {
            return null;
        }
        return object;
    }

    public static Object serialize(String path, Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), object);
        } catch (IOException exp) {
            return null;
        }
        return deserialize(path, object.getClass());
    }
}
