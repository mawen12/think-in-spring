package com.mawen.think.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * {@link Errors} 错误文案实例
 * 只是输出错误文案，而没有校验
 *
 * @see Errors
 */
public class ErrorsMessageDemo {

    public static void main(String[] args) {
        // 1.创建 User 对象
        User user = new User();
        user.setName("mawen");
        // 2.选择 Errors - BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user, "user");
        // 3.调用 reject 或 rejectValue，表示进行参数校验
        // 生成 ObjectError
        // 生成 FieldError
        errors.reject("user.properties.not.null");
        errors.rejectValue("name", "name.required");

        // 4.获取 Errors 中 ObjectError 和 FieldError
        ObjectError globalError = errors.getGlobalError();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();
        // 5. 通过 ObjectError 和 FieldError 中的 code 和 args 来关联 MessageSource 实现
        StaticMessageSource messageSource = createMessageSource();
        // 6. 输出错误内容
        allErrors.forEach(error -> {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        });
    }

    private static StaticMessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null");
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 属性不能为空");
        return messageSource;
    }


    static class User {

        private Long id;

        private String name;

        public User() {
        }

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
