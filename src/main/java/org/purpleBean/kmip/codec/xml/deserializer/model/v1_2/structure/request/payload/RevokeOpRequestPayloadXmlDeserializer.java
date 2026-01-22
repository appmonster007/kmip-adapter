package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RevokeOpRequestPayload;

import java.io.IOException;

public class RevokeOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<RevokeOpRequestPayload, RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder> {

    public RevokeOpRequestPayloadXmlDeserializer() {
        super(RevokeOpRequestPayload.kmipTag);
    }

    @Override
    protected RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder createBuilder() {
        return RevokeOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.REVOCATION_REASON ->
                    builder.revocationReason(ctxt.readValue(p, RevocationReason.class));
            case KmipTag.Standard.COMPROMISE_OCCURRENCE_DATE ->
                    builder.compromiseOccurrenceDate(ctxt.readValue(p, CompromiseOccurrenceDate.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RevokeOpRequestPayload build(RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
