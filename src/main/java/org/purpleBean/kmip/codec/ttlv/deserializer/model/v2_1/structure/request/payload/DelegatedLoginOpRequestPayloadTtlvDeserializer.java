package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DelegatedLoginOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DelegatedLoginOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DelegatedLoginOpRequestPayload, DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder> {

    public DelegatedLoginOpRequestPayloadTtlvDeserializer() {
        super(DelegatedLoginOpRequestPayload.kmipTag, DelegatedLoginOpRequestPayload.encodingType);
    }

    @Override
    protected DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder createBuilder() {
        return DelegatedLoginOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CREDENTIAL -> builder.credential(mapper.readValue(p, Credential.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DelegatedLoginOpRequestPayload build(DelegatedLoginOpRequestPayload.DelegatedLoginOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}