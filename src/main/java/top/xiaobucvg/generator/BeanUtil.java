package top.xiaobucvg.generator;

import top.xiaobucvg.generator.converter.*;
import top.xiaobucvg.generator.util.DBUtil;
import top.xiaobucvg.generator.util.PropertyUtil;

import java.util.*;

public class BeanUtil {

    private static ConverterChain chain = new ConverterChain();

    static {
        chain.
                addConverter(new BigDecimalConverter(chain)).
                addConverter(new ByteArrayConverter(chain)).
                addConverter(new TimeConverter(chain));
    }

    public static void generate() {
        String target = PropertyUtil.getValue("bean.target");
        String packageInfo = PropertyUtil.getValue("bean.package");
        String isSer = PropertyUtil.getValue("bean.isSerializable");

        if ("".equals(target) || "".equals(packageInfo)) {
            throw new RuntimeException("配置文件必须同时指定bean.target和bean.package");
        }
        if ("".equals(isSer)) {
            isSer = "false";
        }

        BeanGenerator generator = new BeanGenerator(packageInfo, target, Boolean.getBoolean(isSer));
        List<String> tableNames = DBUtil.getTableNames();
        for (String name : tableNames) {
            BeanDescription beanDescription = getBeanDescription(name);
            generator.create(beanDescription);
        }
    }

    private static BeanDescription getBeanDescription(String tableName) {
        BeanDescription beanDescription = new BeanDescription();
        Set<String> importClass = new HashSet<>();
        Map<String, String> beanDesFieldMap = new HashMap<>();
        Map<String, String> mapper = DBUtil.getFieldMapper(tableName);
        beanDescription.setName(tableName);
        mapper.forEach((name, type) -> {
            String afterType = chain.convert(type);
            // 没有被任何一个转换器转换
            if (afterType == null) {
                beanDesFieldMap.put(name, type.substring(type.lastIndexOf(".") + 1));
            }
            // 被转换器转换了，而且需要引入某个类
            else if (afterType.contains(".")) {
                importClass.add(afterType);
                beanDesFieldMap.put(name, afterType.substring(afterType.lastIndexOf(".") + 1));
            }
            // 被转换器转换了，但是不需要引入类
            else {
                beanDesFieldMap.put(name, afterType);
            }
            chain.resetIndex();
        });
        beanDescription.setImportClasess(importClass);
        beanDescription.setFieldMapper(beanDesFieldMap);
        return beanDescription;
    }
}
