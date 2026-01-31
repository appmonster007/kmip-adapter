package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

import java.io.IOException;

public class ProtocolVersionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtocolVersion, ProtocolVersion.ProtocolVersionBuilder> {

    public ProtocolVersionXmlDeserializer() {
        super(ProtocolVersion.kmipTag, ProtocolVersion.encodingType);
    }

    @Override
    protected ProtocolVersion.ProtocolVersionBuilder createBuilder() {
        return ProtocolVersion.builder();
    }

    @Override
    protected void setValue(ProtocolVersion.ProtocolVersionBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
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