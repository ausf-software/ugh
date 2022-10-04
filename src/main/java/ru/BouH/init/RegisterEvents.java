package ru.BouH.init;

import ru.BouH.events.CommandBase;
import ru.BouH.utils.EventCommand;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegisterEvents {
    private final Map<String, Method> methodMap = new HashMap<>();

    @SuppressWarnings("rawtypes")
    public void registerEventClass(String eventClass) {
        try {
            Class aClass = Class.forName(eventClass);
            Object object = aClass.newInstance();
            if (object instanceof CommandBase) {
                Method[] methods = aClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(EventCommand.class)) {
                        EventCommand eventCommand = method.getAnnotation(EventCommand.class);
                        this.methodMap.put(eventCommand.command(), method);
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Method> getMethodMap() {
        return this.methodMap;
    }
}
