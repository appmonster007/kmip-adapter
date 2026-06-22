package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.request.payload;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DeactivationDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.DeactivationReason;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.DeactivateOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DeactivateOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeactivateOpRequestPayload, DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder> {

    public DeactivateOpRequestPayloadTtlvDeserializer() {
        super(DeactivateOpRequestPayload.kmipTag, DeactivateOpRequestPayload.encodingType);
    }

    @Override
    protected DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder createBuilder() {
        return DeactivateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.DEACTIVATION_REASON -> builder.deactivationReason(mapper.readValue(p, DeactivationReason.class));
            case KmipTag.Standard.DEACTIVATION_DATE -> builder.deactivationDate(mapper.readValue(p, DeactivationDate.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeactivateOpRequestPayload build(DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}