package cn.ytxu.xhttp_wrapper.model.response;

import cn.ytxu.xhttp_wrapper.apidocjs.bean.Bean;
import cn.ytxu.xhttp_wrapper.model.BaseModel;
import cn.ytxu.xhttp_wrapper.model.field.ExampleModel;
import cn.ytxu.xhttp_wrapper.model.field.FieldGroupContainer;
import cn.ytxu.xhttp_wrapper.model.field.FieldGroupModel;
import cn.ytxu.xhttp_wrapper.model.request.RequestModel;

import java.util.Collections;
import java.util.List;

public class ResponseGroupModel extends BaseModel<RequestModel, Bean> implements FieldGroupContainer<ResponseGroupModel> {

    private List<FieldGroupModel<ResponseGroupModel>> fieldGroups = Collections.EMPTY_LIST;
    private List<ExampleModel<ResponseGroupModel>> examples = Collections.EMPTY_LIST;

    public ResponseGroupModel(RequestModel higherLevel, Bean element) {
        super(higherLevel, element);
    }

    @Override
    public List<FieldGroupModel<ResponseGroupModel>> getFieldGroups() {
        return fieldGroups;
    }

    @Override
    public void setFieldGroups(List<FieldGroupModel<ResponseGroupModel>> fieldGroups) {
        this.fieldGroups = fieldGroups;
    }

    public List<ExampleModel<ResponseGroupModel>> getExamples() {
        return examples;
    }

    public void setExamples(List<ExampleModel<ResponseGroupModel>> examples) {
        this.examples = examples;
    }
}