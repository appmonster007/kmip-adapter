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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateSplitKeyOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CreateSplitKeyOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CreateSplitKeyOpRequestPayload, CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder> {

    public CreateSplitKeyOpRequestPayloadTtlvDeserializer() {
        super(CreateSplitKeyOpRequestPayload.kmipTag, CreateSplitKeyOpRequestPayload.encodingType);
    }

    @Override
    protected CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder createBuilder() {
        return CreateSplitKeyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.SPLIT_KEY_PARTS -> builder.splitKeyParts(mapper.readValue(p, SplitKeyParts.class));
            case KmipTag.Standard.SPLIT_KEY_THRESHOLD -> builder.splitKeyThreshold(mapper.readValue(p, SplitKeyThreshold.class));
            case KmipTag.Standard.SPLIT_KEY_METHOD -> builder.splitKeyMethod(mapper.readValue(p, SplitKeyMethod.class));
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateSplitKeyOpRequestPayload build(CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}