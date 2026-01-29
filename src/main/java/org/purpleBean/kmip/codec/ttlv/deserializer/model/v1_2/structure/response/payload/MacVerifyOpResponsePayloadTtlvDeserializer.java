package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.MacVerifyOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MacVerifyOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MacVerifyOpResponsePayload, MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder> {

    public MacVerifyOpResponsePayloadTtlvDeserializer() {
        super(MacVerifyOpResponsePayload.kmipTag, MacVerifyOpResponsePayload.encodingType);
    }

    @Override
    protected MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder createBuilder() {
        return MacVerifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.VALIDITY_INDICATOR ->
                    builder.validityIndicator(mapper.readValue(p, ValidityIndicator.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected MacVerifyOpResponsePayload build(MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
