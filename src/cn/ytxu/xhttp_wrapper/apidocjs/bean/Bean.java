package cn.ytxu.xhttp_wrapper.apidocjs.bean;

import java.util.List;
import java.util.Map;

/**
 * request:header,parameter
 * response:success,error
 *
 */
public class Bean {
    /**
     * 在request与response中的field
     * k:field group name
     * 1，若为response，则为parent name
     * v:fields
     */
    private Map<String, List<FieldBean>> fields;
    /**
     * 若为request，则为field value example
     * 若为response，则为该response example
     */
    private List<ExampleBean> examples;

    public Map<String, List<FieldBean>> getFields() {
        return fields;
    }

    public void setFields(Map<String, List<FieldBean>> fields) {
        this.fields = fields;
    }

    public List<ExampleBean> getExamples() {
        return examples;
    }

    public void setExamples(List<ExampleBean> examples) {
        this.examples = examples;
    }
}