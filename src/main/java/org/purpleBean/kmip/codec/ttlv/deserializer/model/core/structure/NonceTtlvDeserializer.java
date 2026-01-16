package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NonceTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<Nonce, Nonce.NonceBuilder> {

    public NonceTtlvDeserializer() {
        super(Nonce.kmipTag);
    }

    @Override
    protected Nonce.NonceBuilder createBuilder() {
        return Nonce.builder();
    }

    @Override
    protected void setValue(Nonce.NonceBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.NONCE_ID -> builder.nonceId(mapper.readValue(p, NonceId.class));
            case KmipTag.Standard.NONCE_VALUE -> builder.nonceValue(mapper.readValue(p, NonceValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Nonce build(Nonce.NonceBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return Nonce.encodingType;
    }
}