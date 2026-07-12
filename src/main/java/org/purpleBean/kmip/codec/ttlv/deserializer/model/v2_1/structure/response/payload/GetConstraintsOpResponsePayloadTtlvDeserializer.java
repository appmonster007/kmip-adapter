package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.GetConstraintsOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Constraints;

public class GetConstraintsOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<GetConstraintsOpResponsePayload, GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder> {

    public GetConstraintsOpResponsePayloadTtlvDeserializer() {
        super(GetConstraintsOpResponsePayload.kmipTag, GetConstraintsOpResponsePayload.encodingType);
    }

    @Override
    protected GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder createBuilder() {
        return GetConstraintsOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CONSTRAINTS -> builder.constraints(mapper.readValue(p, Constraints.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetConstraintsOpResponsePayload build(GetConstraintsOpResponsePayload.GetConstraintsOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}