package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetAttributesOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetAttributesOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<GetAttributesOpRequestPayload, GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder> {

    public GetAttributesOpRequestPayloadTtlvDeserializer() {
        super(GetAttributesOpRequestPayload.kmipTag, GetAttributesOpRequestPayload.encodingType);
    }

    @Override
    protected GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder createBuilder() {
        return GetAttributesOpRequestPayload.builder();
    }

    @Override
    protected void setValue(GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(mapper.readValue(p, AttributeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetAttributesOpRequestPayload build(GetAttributesOpRequestPayload.GetAttributesOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
