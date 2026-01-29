package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.MacOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MacOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MacOpResponsePayload, MacOpResponsePayload.MacOpResponsePayloadBuilder> {

    public MacOpResponsePayloadTtlvDeserializer() {
        super(MacOpResponsePayload.kmipTag, MacOpResponsePayload.encodingType);
    }

    @Override
    protected MacOpResponsePayload.MacOpResponsePayloadBuilder createBuilder() {
        return MacOpResponsePayload.builder();
    }

    @Override
    protected void setValue(MacOpResponsePayload.MacOpResponsePayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.MAC_DATA -> builder.macData(mapper.readValue(p, MacData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected MacOpResponsePayload build(MacOpResponsePayload.MacOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
