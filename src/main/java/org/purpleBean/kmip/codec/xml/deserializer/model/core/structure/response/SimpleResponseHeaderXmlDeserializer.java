package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;

import java.io.IOException;

public class SimpleResponseHeaderXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SimpleResponseHeader, SimpleResponseHeader.SimpleResponseHeaderBuilder> {

    public SimpleResponseHeaderXmlDeserializer() {
        super(SimpleResponseHeader.kmipTag);
    }

    @Override
    protected SimpleResponseHeader.SimpleResponseHeaderBuilder createBuilder() {
        return SimpleResponseHeader.builder();
    }

    @Override
    protected void setValue(SimpleResponseHeader.SimpleResponseHeaderBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION -> builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
            default -> {
            }
        }
    }

    @Override
    protected SimpleResponseHeader build(SimpleResponseHeader.SimpleResponseHeaderBuilder builder) {
        return builder.build();
    }
}
