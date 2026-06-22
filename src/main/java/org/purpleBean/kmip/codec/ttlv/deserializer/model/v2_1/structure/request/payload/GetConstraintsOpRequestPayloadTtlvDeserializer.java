package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.GetConstraintsOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetConstraintsOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<GetConstraintsOpRequestPayload, GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder> {

    public GetConstraintsOpRequestPayloadTtlvDeserializer() {
        super(GetConstraintsOpRequestPayload.kmipTag, GetConstraintsOpRequestPayload.encodingType);
    }

    @Override
    protected GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder createBuilder() {
        return GetConstraintsOpRequestPayload.builder();
    }

    @Override
    protected void setValue(GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetConstraintsOpRequestPayload build(GetConstraintsOpRequestPayload.GetConstraintsOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}