package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.RevocationMessage;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.model.core.structure.RevocationReason;

import java.io.IOException;

public class RevocationReasonJsonDeserializer extends AbstractKmipStructureJsonDeserializer<RevocationReason, RevocationReason.RevocationReasonBuilder> {

    public RevocationReasonJsonDeserializer() {
        super(RevocationReason.kmipTag, RevocationReason.encodingType);
    }

    @Override
    protected RevocationReason.RevocationReasonBuilder createBuilder() {
        return RevocationReason.builder();
    }

    @Override
    protected void setValue(RevocationReason.RevocationReasonBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.REVOCATION_REASON_CODE ->
                    builder.revocationReasonCode(ctxt.readValue(p, RevocationReasonCode.class));
            case KmipTag.Standard.REVOCATION_MESSAGE ->
                    builder.revocationMessage(ctxt.readValue(p, RevocationMessage.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RevocationReason build(RevocationReason.RevocationReasonBuilder builder) {
        return builder.build();
    }
}