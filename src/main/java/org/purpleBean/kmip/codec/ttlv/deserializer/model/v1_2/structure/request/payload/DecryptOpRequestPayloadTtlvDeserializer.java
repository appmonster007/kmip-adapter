package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.DecryptOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DecryptOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DecryptOpRequestPayload, DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder> {

    public DecryptOpRequestPayloadTtlvDeserializer() {
        super(DecryptOpRequestPayload.kmipTag, DecryptOpRequestPayload.encodingType);
    }

    @Override
    protected DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder createBuilder() {
        return DecryptOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
            case KmipTag.Standard.IV_COUNTER_NONCE -> builder.ivCounterNonce(mapper.readValue(p, IVCounterNonce.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DecryptOpRequestPayload build(DecryptOpRequestPayload.DecryptOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
