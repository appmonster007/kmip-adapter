package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetAttributeListOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class GetAttributeListOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<GetAttributeListOpRequestPayload, GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder> {

    public GetAttributeListOpRequestPayloadTtlvDeserializer() {
        super(GetAttributeListOpRequestPayload.kmipTag);
    }

    @Override
    protected GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder createBuilder() {
        return GetAttributeListOpRequestPayload.builder();
    }

    @Override
    protected void setValue(GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetAttributeListOpRequestPayload build(GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return GetAttributeListOpRequestPayload.encodingType;
    }
}
