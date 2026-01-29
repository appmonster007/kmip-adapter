package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SecretData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SecretDataTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SecretData, SecretData.SecretDataBuilder> {

    public SecretDataTtlvDeserializer() {
        super(SecretData.kmipTag, SecretData.encodingType);
    }

    @Override
    protected SecretData.SecretDataBuilder createBuilder() {
        return SecretData.builder();
    }

    @Override
    protected void setValue(SecretData.SecretDataBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}