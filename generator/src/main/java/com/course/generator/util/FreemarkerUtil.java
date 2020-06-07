package com.course.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-06-06 19:23
 */
public class FreemarkerUtil {

    static String ftlPath = "generator/src/main/java/com/course/generator/ftl/";
    static Template template;

    public static void initConfig(String ftlName) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setDirectoryForTemplateLoading(new File(ftlPath));
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_0));
        template = configuration.getTemplate(ftlName);
    }

    public static void generator(String fileName, Map<String, String> map) throws IOException, TemplateException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        template.process(map, bw);
        bw.flush();
        fw.close();
    }

}
