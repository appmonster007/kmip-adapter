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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ReProvisionOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ReProvisionOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ReProvisionOpResponsePayload, ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder> {

    public ReProvisionOpResponsePayloadTtlvDeserializer() {
        super(ReProvisionOpResponsePayload.kmipTag, ReProvisionOpResponsePayload.encodingType);
    }

    @Override
    protected ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder createBuilder() {
        return ReProvisionOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ReProvisionOpResponsePayload build(ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}