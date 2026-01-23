package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.MacVerifyOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MacVerifyOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<MacVerifyOpResponsePayload, MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder> {

    public MacVerifyOpResponsePayloadTtlvDeserializer() {
        super(MacVerifyOpResponsePayload.kmipTag);
    }

    @Override
    protected MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder createBuilder() {
        return MacVerifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(MacVerifyOpResponsePayload.MacVerifyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return MacVerifyOpResponsePayload.encodingType;
    }
}
