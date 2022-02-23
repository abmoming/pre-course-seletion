package pers.justin.preselectioncourses.config;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * @author Justin on 2022-02-20 11:27
 * 先继承类 MapWrapper,重写findProperty,通过useCamelCaseMapping来判断是否开启使用驼峰
 */
public class CustomWrapper extends MapWrapper {

    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping) {
            // 使用guava库的转驼峰方法
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
        }
         return name;
    }
}
