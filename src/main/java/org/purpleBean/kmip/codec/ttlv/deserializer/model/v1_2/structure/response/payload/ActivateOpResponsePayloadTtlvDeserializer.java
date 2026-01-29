package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ActivateOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ActivateOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ActivateOpResponsePayload, ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder> {

    public ActivateOpResponsePayloadTtlvDeserializer() {
        super(ActivateOpResponsePayload.kmipTag, ActivateOpResponsePayload.encodingType);
    }

    @Override
    protected ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder createBuilder() {
        return ActivateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ActivateOpResponsePayload build(ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
