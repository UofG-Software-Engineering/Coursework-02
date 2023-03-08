package uofg.se.group.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uofg.se.group.pojo.entity.BaseEntity;
import uofg.se.group.pojo.entity.Skill;

/**
 * @Description Json file reader
 * @Author Xiaohui Yu
 * @Date 2023/2/28
 */
@Component
public class JsonReader {

    ObjectMapper objectMapper = new ObjectMapper();

    public List<?> read(String filePath, Class<?> clazz) {
        File file = new File(filePath);
        if (!file.exists()) {
            // If the file does not exist, create a new file
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            // Read the json file and convert it to a list of objects
            List<?> o = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
            return o;
        } catch (IOException e) {
            // e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
