package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetAttributesOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetAttributesOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<GetAttributesOpResponsePayload, GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder> {

    public GetAttributesOpResponsePayloadTtlvDeserializer() {
        super(GetAttributesOpResponsePayload.kmipTag, GetAttributesOpResponsePayload.encodingType);
    }

    @Override
    protected GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder createBuilder() {
        return GetAttributesOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetAttributesOpResponsePayload build(GetAttributesOpResponsePayload.GetAttributesOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
