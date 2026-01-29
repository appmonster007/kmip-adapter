package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DeleteAttributeOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DeleteAttributeOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeleteAttributeOpRequestPayload, DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder> {

    public DeleteAttributeOpRequestPayloadTtlvDeserializer() {
        super(DeleteAttributeOpRequestPayload.kmipTag, DeleteAttributeOpRequestPayload.encodingType);
    }

    @Override
    protected DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder createBuilder() {
        return DeleteAttributeOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(mapper.readValue(p, AttributeName.class));
            case KmipTag.Standard.ATTRIBUTE_INDEX -> builder.attributeIndex(mapper.readValue(p, AttributeIndex.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeleteAttributeOpRequestPayload build(DeleteAttributeOpRequestPayload.DeleteAttributeOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
