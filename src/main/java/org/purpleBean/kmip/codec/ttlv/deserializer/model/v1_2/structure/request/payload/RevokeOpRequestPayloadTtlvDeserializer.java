package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RevokeOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RevokeOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<RevokeOpRequestPayload, RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder> {

    public RevokeOpRequestPayloadTtlvDeserializer() {
        super(RevokeOpRequestPayload.kmipTag);
    }

    @Override
    protected RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder createBuilder() {
        return RevokeOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.REVOCATION_REASON ->
                    builder.revocationReason(mapper.readValue(p, RevocationReason.class));
            case KmipTag.Standard.COMPROMISE_OCCURRENCE_DATE ->
                    builder.compromiseOccurrenceDate(mapper.readValue(p, CompromiseOccurrenceDate.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RevokeOpRequestPayload build(RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return RevokeOpRequestPayload.encodingType;
    }
}
