package cn.ytxu.api_semi_auto_creater.model.status_code;

import cn.ytxu.api_semi_auto_creater.model.BaseModel;

/**
 * Created by ytxu on 2016/8/30.<br>
 * 状态码的desc格式：<br>
 * Field            Description<br>
 * OK               (0, '')<br>
 * UNAUTHORIZED     (1, '登录状态已过期，请重新登入')<br>
 * SERVER_ERROR     (5, '服务器错误') # 5XX 服务器错误<br>
 *
 * Field        format:statusCodeName<br>
 * Description  format1：statusCodeNumber, statusCodeDesc<br>
 *     statusCodeDesc String-->statusCodeDesc<br>
 * Description  format2：(statusCodeNumber, statusCodeDesc)<br>
 *     statusCodeDesc String-->statusCodeDesc)<br>
 * Description  format3：(statusCodeNumber, statusCodeDesc)xxx<br>
 *     statusCodeDesc String-->statusCodeDesc)xxx<br>
 */
public class StatusCodeModel extends BaseModel<StatusCodeCategoryModel> {
    private final String name;
    private final String value;
    private final String desc;

    public StatusCodeModel(StatusCodeCategoryModel higherLevel, String name, String value, String desc) {
        super(higherLevel, null);
        this.name = name;
        this.value = value;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
