package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

import java.io.IOException;

public class ProtocolVersionJsonDeserializer extends AbstractKmipStructureJsonDeserializer<ProtocolVersion, ProtocolVersion.ProtocolVersionBuilder> {

    public ProtocolVersionJsonDeserializer() {
        super(ProtocolVersion.kmipTag, ProtocolVersion.encodingType);
    }

    @Override
    protected ProtocolVersion.ProtocolVersionBuilder createBuilder() {
        return ProtocolVersion.builder();
    }

    @Override
    protected void setValue(ProtocolVersion.ProtocolVersionBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION_MAJOR ->
                    builder.protocolVersionMajor(ctxt.readValue(p, ProtocolVersionMajor.class));
            case KmipTag.Standard.PROTOCOL_VERSION_MINOR ->
                    builder.protocolVersionMinor(ctxt.readValue(p, ProtocolVersionMinor.class));
            default ->
                    ctxt.reportWrongTokenException(ProtocolVersion.class, p.currentToken(), "Unexpected field " + p.currentName());
        }
    }

    @Override
    protected ProtocolVersion build(ProtocolVersion.ProtocolVersionBuilder builder) {
        return builder.build();
    }
}