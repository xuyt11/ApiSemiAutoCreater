package cn.ytxu.api_semi_auto_creater.model.request;

import cn.ytxu.api_semi_auto_creater.model.BaseModel;
import cn.ytxu.api_semi_auto_creater.model.RequestModel;
import org.jsoup.nodes.Element;

/**
 * Created by ytxu on 2016/6/16.
 */
public class InputParamModel extends BaseModel<RequestModel> {
    private String name;// 字段名称
    private String type;// 字段的类型
    private boolean isOptional = false;// 是否为可选字段
    private DefinedParamModel defind;// 已定义的字段描述对象

    public InputParamModel(RequestModel higherLevel, Element element) {
        super(higherLevel, element);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public void setDefind(DefinedParamModel defind) {
        this.defind = defind;
    }
}