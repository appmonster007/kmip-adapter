package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.common.structure.RevocationReason;

import java.io.IOException;

public class RevocationReasonXmlDeserializer extends KmipDataTypeXmlDeserializer<RevocationReason> {
    private final KmipTag kmipTag = RevocationReason.kmipTag;

    @Override
    public RevocationReason deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(RevocationReason.class, "Invalid Tag for RevocationReason");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        RevocationReason.RevocationReasonBuilder builder = RevocationReason.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(RevocationReason.class, "Unexpected token: " + p.currentToken());
            }
        }

        RevocationReason revocationReason = builder.build();

        if (!revocationReason.isSupported()) {
            ctxt.reportInputMismatch(RevocationReason.class, "RevocationReason not supported for spec " + spec);
            return null;
        }

        return revocationReason;
    }

    private void setValue(
            RevocationReason.RevocationReasonBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.REVOCATION_REASON_CODE ->
                    builder.revocationReasonCode(ctxt.readValue(p, RevocationReasonCode.class));
            case KmipTag.Standard.REVOCATION_MESSAGE ->
                    builder.revocationMessage(ctxt.readValue(p, RevocationMessage.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}