package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.RotateNameValue;

import java.io.IOException;

public class RotateNameValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RotateNameValue, RotateNameValue.RotateNameValueBuilder> {

    public RotateNameValueXmlDeserializer() {
        super(RotateNameValue.kmipTag, RotateNameValue.encodingType);
    }

    @Override
    protected RotateNameValue.RotateNameValueBuilder createBuilder() {
        return RotateNameValue.builder();
    }

    @Override
    protected void setValue(RotateNameValue.RotateNameValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected RotateNameValue build(RotateNameValue.RotateNameValueBuilder builder) {
        return builder.build();
    }
}
