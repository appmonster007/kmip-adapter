package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SecretData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SecretDataTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SecretData, SecretData.SecretDataBuilder> {

    public SecretDataTtlvDeserializer() {
        super(SecretData.kmipTag);
    }

    @Override
    protected SecretData.SecretDataBuilder createBuilder() {
        return SecretData.builder();
    }

    @Override
    protected void setValue(SecretData.SecretDataBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.SECRET_DATA_TYPE -> builder.secretDataType(mapper.readValue(p, SecretDataType.class));
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SecretData build(SecretData.SecretDataBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SecretData.encodingType;
    }
}