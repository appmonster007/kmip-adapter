package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipStructureXmlDeserializer;

import java.io.IOException;

public class ProtocolVersionXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ProtocolVersion, ProtocolVersion.ProtocolVersionBuilder> {

    public ProtocolVersionXmlDeserializer() {
        super(ProtocolVersion.kmipTag);
    }

    @Override
    protected ProtocolVersion.ProtocolVersionBuilder createBuilder() {
        return ProtocolVersion.builder();
    }

    @Override
    protected void setValue(ProtocolVersion.ProtocolVersionBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION_MAJOR ->
                    builder.protocolVersionMajor(ctxt.readValue(p, ProtocolVersion.ProtocolVersionMajor.class));
            case KmipTag.Standard.PROTOCOL_VERSION_MINOR ->
                    builder.protocolVersionMinor(ctxt.readValue(p, ProtocolVersion.ProtocolVersionMinor.class));
            default ->
                    ctxt.reportWrongTokenException(ProtocolVersion.class, p.currentToken(), "Unexpected field " + p.currentName());
        }
    }

    @Override
    protected ProtocolVersion build(ProtocolVersion.ProtocolVersionBuilder builder) {
        return builder.build();
    }
}