package cn.ytxu.api_semi_auto_creater.parser.response.output.sub;

import cn.ytxu.api_semi_auto_creater.model.response.OutputParamModel;
import cn.ytxu.api_semi_auto_creater.model.response.ResponseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ytxu on 2016/8/24.
 * 获取到response中所有的能生成响应实体文件的output
 */
public class GetOutputsThatCanGenerateResponseEntityFileUtil {
    private ResponseModel response;

    public GetOutputsThatCanGenerateResponseEntityFileUtil(ResponseModel response) {
        this.response = response;
    }

    public List<OutputParamModel> start() {
        List<OutputParamModel> oaOutputs = new GetOAOutputsUtil(response).start();
        return getOutputsThatCanGenerateResponseEntityFile(oaOutputs);
    }

    private List<OutputParamModel> getOutputsThatCanGenerateResponseEntityFile(List<OutputParamModel> oaOutputs) {
        List<OutputParamModel> outputs = new ArrayList<>();
        for (OutputParamModel oaOutput : oaOutputs) {
            if (oaOutput.isDontRequireGenerationResponseEntityFileTag()) {
                continue;
            }
            outputs.add(oaOutput);
        }
        return outputs;
    }
}