package top.ireed.aop;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现Handler登记
 */
@Component /*组件类，并告知Spring要为这个类创建bean*/
public class TaskHandlerRegister extends ApplicationObjectSupport {

    private final static Map<String, AbstractHandler> TASK_HANDLERS_MAP = new HashMap<>();


    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        Map<String, Object> taskBeanMap = context.getBeansWithAnnotation(TypeHandler.class);
        taskBeanMap.keySet().forEach(beanName -> {
            Object bean = taskBeanMap.get(beanName);
            Class<?> clazz = bean.getClass();
            if (bean instanceof AbstractHandler && clazz.getAnnotation(TypeHandler.class) != null) {
                TypeHandler TypeHandler = clazz.getAnnotation(TypeHandler.class);
                String taskType = TypeHandler.type();
                if (TASK_HANDLERS_MAP.containsKey(taskType)) {
                    throw new RuntimeException("TaskType has Exits. TaskType=" + taskType);
                }
                TASK_HANDLERS_MAP.put(TypeHandler.type(), (AbstractHandler) taskBeanMap.get(beanName));
            }
        });
    }

    public static AbstractHandler getTaskHandler(String taskType) {
        return TASK_HANDLERS_MAP.get(taskType);
    }
}