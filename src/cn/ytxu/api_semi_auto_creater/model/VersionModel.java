package cn.ytxu.api_semi_auto_creater.model;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by ytxu on 2016/7/20.
 */
public class VersionModel extends BaseModel<DocModel> {
    private List<SectionModel> sections;

    public VersionModel(DocModel higherLevel, Element element) {
        super(higherLevel, element);
    }


}
