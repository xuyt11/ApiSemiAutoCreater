package cn.ytxu.xhttp_wrapper.apidocjs.parser;

import cn.ytxu.xhttp_wrapper.apidocjs.bean.ApidocjsHelper;
import cn.ytxu.xhttp_wrapper.apidocjs.parser.request.RequestParser;
import cn.ytxu.xhttp_wrapper.apidocjs.parser.response.ResponseSErrorParser;
import cn.ytxu.xhttp_wrapper.apidocjs.parser.status_code.StatusCodeParser;
import cn.ytxu.xhttp_wrapper.config.ConfigWrapper;
import cn.ytxu.xhttp_wrapper.apidocjs.bean.api_data.ApiDataBean;
import cn.ytxu.xhttp_wrapper.common.enums.CompileModel;
import cn.ytxu.xhttp_wrapper.model.ModelHelper;
import cn.ytxu.xhttp_wrapper.model.request.RequestGroupModel;
import cn.ytxu.xhttp_wrapper.model.request.RequestModel;
import cn.ytxu.xhttp_wrapper.model.request.header.HeaderGroupModel;
import cn.ytxu.xhttp_wrapper.model.request.input.InputGroupModel;
import cn.ytxu.xhttp_wrapper.model.response.OutputParamModel;
import cn.ytxu.xhttp_wrapper.model.response.ResponseContainerModel;
import cn.ytxu.xhttp_wrapper.model.response.ResponseModel;
import cn.ytxu.xhttp_wrapper.model.response.field.ResponseFieldGroupModel;
import cn.ytxu.xhttp_wrapper.model.status_code.StatusCodeGroupModel;
import cn.ytxu.xhttp_wrapper.model.version.VersionModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ApidocjsDataParser {
    private List<VersionModel> versions;

    public ApidocjsDataParser() {
    }

    public List<VersionModel> start() throws IOException {
        ModelHelper.reload();
        ApidocjsHelper.reload();
        List<ApiDataBean> apiDatas = ApidocjsHelper.getApiDatasFromFile();
        versions = createVersionModelsByApiDatas(apiDatas);

        parseStatusCode();
        parseRequest();
//        parseResponses();
        parseResponseSErrors();

        sort();
        return versions;
    }

    private void sort() {
        for (VersionModel version : versions) {
            Collections.sort(version.getRequestGroups());
            for (RequestGroupModel requestGroup : version.getRequestGroups()) {
                Collections.sort(requestGroup.getRequests());
                for (RequestModel request : requestGroup.getRequests()) {
                    Collections.sort(request.getHeaderGroups());
                    for (HeaderGroupModel headerGroup : request.getHeaderGroups()) {
                        Collections.sort(headerGroup.getHeaders());
                    }

                    Collections.sort(request.getInputGroups());
                    for (InputGroupModel inputGroup : request.getInputGroups()) {
                        Collections.sort(inputGroup.getInputs());
                    }

                    ResponseContainerModel responseContainer = request.getResponseContainer();
                    Collections.sort(responseContainer.getSuccessFieldGroups());
                    for (ResponseFieldGroupModel responseFieldGroup : responseContainer.getSuccessFieldGroups()) {
                        Collections.sort(responseFieldGroup.getFields());
                    }
                    Collections.sort(responseContainer.getErrorFieldGroups());
                    for (ResponseFieldGroupModel responseFieldGroup : responseContainer.getErrorFieldGroups()) {
                        Collections.sort(responseFieldGroup.getFields());
                    }

                    Collections.sort(responseContainer.getSuccessResponses());
                    for (ResponseModel response : responseContainer.getSuccessResponses()) {
                        Collections.sort(response.getOutputs());
                        sortOutpouts(response.getOutputs());
                    }

                    Collections.sort(responseContainer.getErrorResponses());
                    for (ResponseModel response : responseContainer.getErrorResponses()) {
                        Collections.sort(response.getOutputs());
                        sortOutpouts(response.getOutputs());
                    }
                }
            }

            Collections.sort(version.getStatusCodeGroups());
            for (StatusCodeGroupModel statusCodeGroup : version.getStatusCodeGroups()) {
                Collections.sort(statusCodeGroup.getStatusCodes());
            }

            sortOutpouts(version.getSubsOfErrors());
        }
    }

    private void sortOutpouts(List<OutputParamModel> outputs) {
        for (OutputParamModel output : outputs) {
            Collections.sort(output.getSubs());
            if (output.getSubs().isEmpty()) {
                continue;
            }
            sortOutpouts(output.getSubs());
        }
    }

    private List<VersionModel> createVersionModelsByApiDatas(List<ApiDataBean> apiDatas) {
        CompileModel compileModel = ConfigWrapper.getBaseConfig().getCompileModelType();
        return compileModel.createApiDatasFromApidocJsData(apiDatas);
    }

    private void parseStatusCode() {
        for (VersionModel version : versions) {
            List<StatusCodeGroupModel> statusCodeGroups = version.getStatusCodeGroups();
            new StatusCodeParser(statusCodeGroups).start();
        }
    }

    private void parseRequest() {
        for (VersionModel version : versions) {
            List<RequestGroupModel> requestGroups = version.getRequestGroups();
            new RequestParser(requestGroups).start();
        }
    }

    private void parseResponseSErrors() {
        for (VersionModel version : versions) {
            new ResponseSErrorParser(version).start();
        }
    }

}
