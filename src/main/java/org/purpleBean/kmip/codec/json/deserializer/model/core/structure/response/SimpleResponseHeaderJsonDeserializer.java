package org.purpleBean.kmip.codec.json.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;

import java.io.IOException;

public class SimpleResponseHeaderJsonDeserializer extends AbstractKmipStructureJsonDeserializer<SimpleResponseHeader, SimpleResponseHeader.SimpleResponseHeaderBuilder> {

    public SimpleResponseHeaderJsonDeserializer() {
        super(SimpleResponseHeader.kmipTag, SimpleResponseHeader.encodingType);
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
