package com.navercorp.pinpoint.web.util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * modify renderMergedOutputModel만 수정함.
 * @author emeroad
 */
public class MappingJackson2JsonpView extends MappingJackson2JsonView {

    private static final String cbPrefix = "(";
    private static final String cbSuffix = ")";
    private static final String cbEnd = ";";

    private static final String DEFAULT_CALLBACK_PARAMETER = "_callback";

    public static final String CONTENT_TYPE_JSONP = "application/javascript";

    private ObjectMapper objectMapper;

    private JsonEncoding encoding = JsonEncoding.UTF8;

    private boolean prefixJson = false;

    public MappingJackson2JsonpView() {
        setContentType(CONTENT_TYPE_JSONP);
        setExposePathVariables(false);
        // object mapper change
        setObjectMapper(new ObjectMapper());
    }


    public void setObjectMapper(ObjectMapper objectMapper) {
        super.setObjectMapper(objectMapper);
        // intercept param
        this.objectMapper = objectMapper;
    }


    @Override
    public void setJsonPrefix(String jsonPrefix) {
        super.setJsonPrefix(jsonPrefix);
    }


    @Override
    public void setEncoding(JsonEncoding encoding) {
        super.setEncoding(encoding);
        // intercept param
        this.encoding = encoding;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        Object value = filterModel(model);
        JsonGenerator generator = this.objectMapper.getFactory().createGenerator(response.getOutputStream(), this.encoding);
        if (this.prefixJson) {
            generator.writeRaw("{} && ");
        }
        final String callBackParameter = getCallBackParameter(request);
        if (StringUtils.isEmpty(callBackParameter)) {
            this.objectMapper.writeValue(generator, value);
        } else {
            generator.writeRaw(callBackParameter);
            generator.writeRaw(cbPrefix);
            this.objectMapper.writeValue(generator, value);
            generator.writeRaw(cbSuffix);
            generator.writeRaw(cbEnd);
        }
        generator.flush();
    }

    private String getCallBackParameter(HttpServletRequest request) {
        return request.getParameter(DEFAULT_CALLBACK_PARAMETER);
    }


}
