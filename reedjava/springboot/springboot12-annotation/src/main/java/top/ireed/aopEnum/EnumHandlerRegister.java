package top.ireed.aopEnum;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现Enum Handler登记
 */
@Component /*组件类，并告知Spring要为这个类创建bean*/
public class EnumHandlerRegister extends ApplicationObjectSupport {

    private final static Map<TypeEnum, AbstractEnumHandler> ENUM_HANDLERS_MAP = new HashMap<>();

    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        Map<String, Object> taskBeanMap = context.getBeansWithAnnotation(TypeEnumHandler.class);
        taskBeanMap.keySet().forEach(beanName -> {
            Object bean = taskBeanMap.get(beanName);
            Class<?> clazz = bean.getClass();
            if (bean instanceof AbstractEnumHandler && clazz.getAnnotation(TypeEnumHandler.class) != null) {
                TypeEnumHandler TypeEnumHandler = clazz.getAnnotation(TypeEnumHandler.class);
                TypeEnum typeEnum = TypeEnumHandler.value();
                if (ENUM_HANDLERS_MAP.containsKey(typeEnum)) {
                    throw new RuntimeException("TaskType has Exits. TaskType=" + typeEnum);
                }
                ENUM_HANDLERS_MAP.put(TypeEnumHandler.value(), (AbstractEnumHandler) taskBeanMap.get(beanName));
            }
        });
    }

    public static AbstractEnumHandler getTaskHandler(TypeEnum type) {
        return ENUM_HANDLERS_MAP.get(type);
    }
}