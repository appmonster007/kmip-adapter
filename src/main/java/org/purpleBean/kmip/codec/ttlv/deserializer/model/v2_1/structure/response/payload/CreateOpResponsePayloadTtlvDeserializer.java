package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.CreateOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CreateOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CreateOpResponsePayload, CreateOpResponsePayload.CreateOpResponsePayloadBuilder> {

    public CreateOpResponsePayloadTtlvDeserializer() {
        super(CreateOpResponsePayload.kmipTag, CreateOpResponsePayload.encodingType);
    }

    @Override
    protected CreateOpResponsePayload.CreateOpResponsePayloadBuilder createBuilder() {
        return CreateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(CreateOpResponsePayload.CreateOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateOpResponsePayload build(CreateOpResponsePayload.CreateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
