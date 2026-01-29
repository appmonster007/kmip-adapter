package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetAttributeListOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetAttributeListOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<GetAttributeListOpResponsePayload, GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder> {

    public GetAttributeListOpResponsePayloadTtlvDeserializer() {
        super(GetAttributeListOpResponsePayload.kmipTag, GetAttributeListOpResponsePayload.encodingType);
    }

    @Override
    protected GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder createBuilder() {
        return GetAttributeListOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(mapper.readValue(p, AttributeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetAttributeListOpResponsePayload build(GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
