package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.AttributeValueStructure;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import java.io.IOException;

public class SimpleRequestHeaderXmlDeserializer extends KmipDataTypeXmlDeserializer<SimpleRequestHeader> {
    private final KmipTag kmipTag = SimpleRequestHeader.kmipTag;
    private final EncodingType encodingType = SimpleRequestHeader.encodingType;

    @Override
    public SimpleRequestHeader deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(AttributeValueStructure.class, "Invalid Tag for SimpleRequestHeader");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestHeader.SimpleRequestHeaderBuilder builder = SimpleRequestHeader.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken(); // Move to the value token
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(SimpleRequestHeader.class, "Unexpected token: " + p.currentToken());
            }
        }

        SimpleRequestHeader header = builder.build();

        if (!header.isSupported()) {
            ctxt.reportInputMismatch(SimpleRequestHeader.class, "SimpleRequestHeader not supported for spec " + spec);
            return null;
        }

        return header;
    }

    private void setValue(
            SimpleRequestHeader.SimpleRequestHeaderBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION -> builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
