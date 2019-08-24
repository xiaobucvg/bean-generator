package top.xiaobucvg.generator;

import java.util.Map;
import java.util.Set;

/***
 * 一个bean的描述
 *
 * by Mr.Zhang
 */
public class BeanDescription {
    private String name;
    private Map<String, String> fieldMapper;
    // 需要引入的类
    private Set<String> importClasess;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getFieldMapper() {
        return fieldMapper;
    }

    public void setFieldMapper(Map<String, String> fieldMapper) {
        this.fieldMapper = fieldMapper;
    }

    public Set<String> getImportClasess() {
        return importClasess;
    }

    public void setImportClasess(Set<String> importClasess) {
        this.importClasess = importClasess;
    }
}
