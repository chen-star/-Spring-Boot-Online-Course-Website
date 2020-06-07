package com.course.generator.vue;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreemarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-06-06 19:26
 */
public class VueGenerator {

    static String MODULE = "business";
    static String toVuePath = "admin/src/views/admin/";
    static String generatorConfigPath = "server/src/main/resources/generator/generatorConfig.xml";

    public static void main(String[] args) throws Exception {
        String module = MODULE;

        File file = new File(generatorConfigPath);
        SAXReader reader = new SAXReader();
        Document doc = reader.read(file);
        Element rootElement = doc.getRootElement();
        Element contextElement = rootElement.element("context");
        Element tableElement;

        tableElement = contextElement.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("Table: " + tableName);
        System.out.println("Domain: " + Domain);

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        FreemarkerUtil.initConfig("vue.ftl");
        FreemarkerUtil.generator(toVuePath + domain + ".vue", map);
    }

    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
