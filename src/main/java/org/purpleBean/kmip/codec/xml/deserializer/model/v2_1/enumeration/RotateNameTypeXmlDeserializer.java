package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.RotateNameType;

import java.io.IOException;

public class RotateNameTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RotateNameType, RotateNameType.RotateNameTypeBuilder> {

    public RotateNameTypeXmlDeserializer() {
        super(RotateNameType.kmipTag, RotateNameType.encodingType);
    }

    @Override
    protected RotateNameType.RotateNameTypeBuilder createBuilder() {
        return RotateNameType.builder();
    }

    @Override
    protected void setValue(RotateNameType.RotateNameTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(RotateNameType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected RotateNameType build(RotateNameType.RotateNameTypeBuilder builder) {
        return builder.build();
    }
}