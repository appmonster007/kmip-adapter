package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DecryptOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DecryptOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<DecryptOpResponsePayload, DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder> {

    public DecryptOpResponsePayloadTtlvDeserializer() {
        super(DecryptOpResponsePayload.kmipTag);
    }

    @Override
    protected DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder createBuilder() {
        return DecryptOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DecryptOpResponsePayload build(DecryptOpResponsePayload.DecryptOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return DecryptOpResponsePayload.encodingType;
    }
}
