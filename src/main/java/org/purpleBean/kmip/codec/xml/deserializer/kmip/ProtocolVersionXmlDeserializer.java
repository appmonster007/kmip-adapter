package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;

import java.io.IOException;

public class ProtocolVersionXmlDeserializer extends KmipDataTypeXmlDeserializer<ProtocolVersion> {

    private final KmipTag kmipTag = ProtocolVersion.kmipTag;
    private final EncodingType encodingType = ProtocolVersion.encodingType;

    @Override
    public ProtocolVersion deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(ProtocolVersion.class, "Invalid Tag for ProtocolVersion");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        ProtocolVersion.ProtocolVersionBuilder builder = ProtocolVersion.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(ProtocolVersion.class, "Unexpected token: " + p.currentToken());
            }
        }

        ProtocolVersion protocolVersion = builder.build();

        if (!protocolVersion.isSupported()) {
            ctxt.reportInputMismatch(ProtocolVersion.class,
                    "ProtocolVersion not supported for spec " + spec);
        }

        return protocolVersion;
    }

    private void setValue(
            ProtocolVersion.ProtocolVersionBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION_MAJOR ->
                    builder.protocolVersionMajor(ctxt.readValue(p, ProtocolVersion.ProtocolVersionMajor.class));
            case KmipTag.Standard.PROTOCOL_VERSION_MINOR ->
                    builder.protocolVersionMinor(ctxt.readValue(p, ProtocolVersion.ProtocolVersionMinor.class));
            default ->
                    ctxt.reportWrongTokenException(ProtocolVersion.class, p.currentToken(), "Unexpected field " + p.currentName());
        }
    }
}
