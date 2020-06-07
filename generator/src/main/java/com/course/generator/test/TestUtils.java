package com.course.generator.test;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-06-06 19:05
 */
public class TestUtils {

    static String ftlPath = "generator/src/main/java/com/course/generator/test/";
    static String toPath = "generator/src/main/java/com/course/generator/test/";

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setDirectoryForTemplateLoading(new File(ftlPath));
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_0));
        Template template = configuration.getTemplate("test.ftl");

        FileWriter fw = new FileWriter(toPath + "Test.java");
        BufferedWriter bw = new BufferedWriter(fw);
        template.process(null, bw);
        bw.flush();
        fw.close();
    }
}
