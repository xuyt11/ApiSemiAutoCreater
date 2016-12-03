package cn.ytxu.xhttp_wrapper.model.request;

import cn.ytxu.util.FileUtil;
import cn.ytxu.xhttp_wrapper.model.BaseModel;
import cn.ytxu.xhttp_wrapper.model.version.VersionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ytxu on 2016-9-18
 */
public class RequestGroupModel extends BaseModel<VersionModel, String> {
    private String name;
    private List<RequestModel> requests = Collections.EMPTY_LIST;

    public RequestGroupModel(VersionModel higherLevel, String groupName) {
        super(higherLevel, groupName);
        this.name = groupName;
    }

    public String getName() {
        return name;
    }


    public void addRequest(RequestModel request) {
        if (requests == Collections.EMPTY_LIST) {
            requests = new ArrayList<>(10);
        }
        requests.add(request);
    }

    public List<RequestModel> getRequests() {
        return requests;
    }


    //*************** reflect method area ***************

    public String section_class_name() {
        String className = FileUtil.getClassFileName(getName());
        return className;
    }

    public String section() {
        return FileUtil.getPackageName(getName());
    }

    public String section_newchama() {
        return FileUtil.getCategoryPackageName(getName());
    }

    public String section_name() {
        String className = section_class_name();
        String fieldName = className.substring(0, 1).toLowerCase() + className.substring(1);
        if ("notify".equals(fieldName)) {
            fieldName += "0";
        }
        return fieldName;
    }

    public List requests() {
        return requests;
    }
}
