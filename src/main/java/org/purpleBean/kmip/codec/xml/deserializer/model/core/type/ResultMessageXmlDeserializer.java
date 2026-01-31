package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ResultMessage;

import java.io.IOException;

public class ResultMessageXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ResultMessage, ResultMessage.ResultMessageBuilder> {

    public ResultMessageXmlDeserializer() {
        super(ResultMessage.kmipTag, ResultMessage.encodingType);
    }

    @Override
    protected ResultMessage.ResultMessageBuilder createBuilder() {
        return ResultMessage.builder();
    }

    @Override
    protected void setValue(ResultMessage.ResultMessageBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected ResultMessage build(ResultMessage.ResultMessageBuilder builder) {
        return builder.build();
    }
}