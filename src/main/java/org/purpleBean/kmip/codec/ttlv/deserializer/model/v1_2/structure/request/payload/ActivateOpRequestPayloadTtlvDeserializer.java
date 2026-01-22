package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ActivateOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ActivateOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<ActivateOpRequestPayload, ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder> {

    public ActivateOpRequestPayloadTtlvDeserializer() {
        super(ActivateOpRequestPayload.kmipTag);
    }

    @Override
    protected ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder createBuilder() {
        return ActivateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ActivateOpRequestPayload build(ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return ActivateOpRequestPayload.encodingType;
    }
}
