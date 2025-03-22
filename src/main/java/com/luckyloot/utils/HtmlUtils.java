package com.luckyloot.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class HtmlUtils {

    public static String readHtmlContent(String templatePath) {
        try {
            ClassPathResource resource = new ClassPathResource(templatePath);
            byte[] fileBytes = new byte[(int) resource.contentLength()];
            resource.getInputStream().read(fileBytes);
            return new String(fileBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "Cannot read html content";
        }
    }

}
