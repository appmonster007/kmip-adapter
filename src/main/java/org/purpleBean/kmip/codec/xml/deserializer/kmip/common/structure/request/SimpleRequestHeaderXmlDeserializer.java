package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.structure.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import java.io.IOException;

public class SimpleRequestHeaderXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SimpleRequestHeader, SimpleRequestHeader.SimpleRequestHeaderBuilder> {

    public SimpleRequestHeaderXmlDeserializer() {
        super(SimpleRequestHeader.kmipTag);
    }

    @Override
    protected SimpleRequestHeader.SimpleRequestHeaderBuilder createBuilder() {
        return SimpleRequestHeader.builder();
    }

    @Override
    protected void setValue(SimpleRequestHeader.SimpleRequestHeaderBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION -> builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SimpleRequestHeader build(SimpleRequestHeader.SimpleRequestHeaderBuilder builder) {
        return builder.build();
    }
}