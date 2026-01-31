package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;

import java.io.IOException;

public class ProtocolVersionMajorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtocolVersionMajor, ProtocolVersionMajor.ProtocolVersionMajorBuilder> {

    public ProtocolVersionMajorXmlDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType);
    }

    @Override
    protected ProtocolVersionMajor.ProtocolVersionMajorBuilder createBuilder() {
        return ProtocolVersionMajor.builder();
    }

    @Override
    protected void setValue(ProtocolVersionMajor.ProtocolVersionMajorBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected ProtocolVersionMajor build(ProtocolVersionMajor.ProtocolVersionMajorBuilder builder) {
        return builder.build();
    }
}