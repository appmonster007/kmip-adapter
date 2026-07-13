package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.AddAttributeOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AddAttributeOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AddAttributeOpResponsePayload, AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder> {

    public AddAttributeOpResponsePayloadTtlvDeserializer() {
        super(AddAttributeOpResponsePayload.kmipTag, AddAttributeOpResponsePayload.encodingType);
    }

    @Override
    protected AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder createBuilder() {
        return AddAttributeOpResponsePayload.builder();
    }

    @Override
    protected void setValue(AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected AddAttributeOpResponsePayload build(AddAttributeOpResponsePayload.AddAttributeOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}