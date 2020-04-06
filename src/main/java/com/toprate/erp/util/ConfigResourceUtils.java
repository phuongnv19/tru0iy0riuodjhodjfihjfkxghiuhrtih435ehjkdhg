package com.toprate.erp.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public abstract class ConfigResourceUtils {
    private final static String BASE_NAME = "config_content";

    public static String getContent(String code, Locale locale) {
        return getContent(code, locale, null);
    }

    public static String getContent(String code, Locale locale, Object... args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
        String message;
        try {
            message = resourceBundle.getString(code);
            message = MessageFormat.format(message, args);
        } catch (Exception ex) {
            log.info(">>> Can not get message with code {}", code);
            log.info(ex.getMessage(), ex);
            message = code;
        }

        return message;
    }

    public static String getContent(String code) {

        return getContent(code, LocaleContextHolder.getLocale(), null);
    }

    public static String getContent(String code, Object... args) {
        return getContent(code, LocaleContextHolder.getLocale(), args);
    }
}
