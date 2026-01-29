package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DeleteAttributeOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DeleteAttributeOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeleteAttributeOpResponsePayload, DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder> {

    public DeleteAttributeOpResponsePayloadTtlvDeserializer() {
        super(DeleteAttributeOpResponsePayload.kmipTag, DeleteAttributeOpResponsePayload.encodingType);
    }

    @Override
    protected DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder createBuilder() {
        return DeleteAttributeOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeleteAttributeOpResponsePayload build(DeleteAttributeOpResponsePayload.DeleteAttributeOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
