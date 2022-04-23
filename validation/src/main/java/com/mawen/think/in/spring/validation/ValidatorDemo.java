package com.mawen.think.in.spring.validation;

import org.springframework.cglib.core.Local;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.*;

import java.util.Locale;

/**
 * 自定义 Spring {@link Validator} 实现
 *
 * @see Validator
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        // 1. 创建 Validator
        UserValidator validator = new UserValidator();
        // 2. 判断是否支持目标对象的类型
        User user = new User();
        user.setId(1L);
        System.out.println("user 对象是否被 UserValidator 支持校验：" + validator.supports(user.getClass()));
        // 3. 创建 Errors 实例
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        // 4. 获取 MessageSource 对象并输出错误
        StaticMessageSource messageSource = createMessageSource();
        for (ObjectError error : errors.getAllErrors()) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            // 判断传递的类是否为User或其子类
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        }
    }

    private static StaticMessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 属性不能为空");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not be null");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null");
        return messageSource;
    }

    static class User {

        private Long id;

        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
