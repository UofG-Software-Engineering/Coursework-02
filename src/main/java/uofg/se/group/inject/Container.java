package uofg.se.group.inject;

import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class Container {

    // 存储Bean对象的Map
    private final Map<String, Object> beanMap = new HashMap<>();

    // 初始化容器，扫描所有的Bean类
    public void init() {
        String[] packageNames = {"uofg.se.group"};
        for (String packageName : packageNames) {
            Set<Class<?>> classes = ClassUtil.getClasses(packageName);
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Singleton.class)) {
                    Object bean = createBean(clazz);
                    beanMap.put(clazz.getName(), bean);
                }
            }
        }
        //注入依赖
        for (Object bean : beanMap.values()) {
            injection(bean);
        }
    }

    // 创建Bean对象
    private Object createBean(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获取Bean对象
    public Object getBean(String name) {
        return beanMap.get(name);
    }

    // 注入依赖
    private void injection(Object bean) {
        Class<?> clazz = bean.getClass();
        Field[] fields = ReflectUtil.getFields(clazz);
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object value = getBean(field.getName());
                if (value == null) {
                    continue;
                }
                try {
                    field.setAccessible(true);
                    field.set(bean, value);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
