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
import org.purpleBean.kmip.model.v2_1.structure.CommonAttributes;
import org.purpleBean.kmip.model.v2_1.structure.PrivateKeyAttributes;
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateKeyPairOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CreateKeyPairOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CreateKeyPairOpRequestPayload, CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder> {

    public CreateKeyPairOpRequestPayloadTtlvDeserializer() {
        super(CreateKeyPairOpRequestPayload.kmipTag, CreateKeyPairOpRequestPayload.encodingType);
    }

    @Override
    protected CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder createBuilder() {
        return CreateKeyPairOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.COMMON_ATTRIBUTES -> builder.commonAttributes(mapper.readValue(p, CommonAttributes.class));
            case KmipTag.Standard.PRIVATE_KEY_ATTRIBUTES -> builder.privateKeyAttributes(mapper.readValue(p, PrivateKeyAttributes.class));
            case KmipTag.Standard.PUBLIC_KEY_ATTRIBUTES -> builder.publicKeyAttributes(mapper.readValue(p, PublicKeyAttributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateKeyPairOpRequestPayload build(CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}