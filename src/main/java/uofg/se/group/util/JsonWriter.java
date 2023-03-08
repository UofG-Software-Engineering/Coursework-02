package uofg.se.group.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @Description Json file writer
 * @Author Xiaohui Yu
 * @Date 2023/2/28
 */
@Component
public class JsonWriter {

    ObjectMapper objectMapper = new ObjectMapper();

    public void write(String filePath, Object object) {
        try {
            objectMapper.writeValue(new File(filePath), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
