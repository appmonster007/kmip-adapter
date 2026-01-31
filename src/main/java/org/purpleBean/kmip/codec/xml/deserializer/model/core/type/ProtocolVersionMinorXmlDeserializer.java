package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

import java.io.IOException;

public class ProtocolVersionMinorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtocolVersionMinor, ProtocolVersionMinor.ProtocolVersionMinorBuilder> {

    public ProtocolVersionMinorXmlDeserializer() {
        super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType);
    }

    @Override
    protected ProtocolVersionMinor.ProtocolVersionMinorBuilder createBuilder() {
        return ProtocolVersionMinor.builder();
    }

    @Override
    protected void setValue(ProtocolVersionMinor.ProtocolVersionMinorBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected ProtocolVersionMinor build(ProtocolVersionMinor.ProtocolVersionMinorBuilder builder) {
        return builder.build();
    }
}