package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KeyValue;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.KeyWrappingData;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyBlockTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<KeyBlock, KeyBlock.KeyBlockBuilder> {

    public KeyBlockTtlvDeserializer() {
        super(KeyBlock.kmipTag);
    }

    @Override
    protected KeyBlock.KeyBlockBuilder createBuilder() {
        return KeyBlock.builder();
    }

    @Override
    protected void setValue(KeyBlock.KeyBlockBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_FORMAT_TYPE -> {
                KeyFormatType keyFormatType = mapper.readValue(p, KeyFormatType.class);
                builder.keyFormatType(keyFormatType);
                mapper.setAttribute("keyFormatType", keyFormatType.getDescription());
            }
            case KmipTag.Standard.KEY_COMPRESSION_TYPE ->
                    builder.keyCompressionType(mapper.readValue(p, KeyCompressionType.class));
            case KmipTag.Standard.KEY_VALUE -> builder.keyValue((KeyValue) mapper.readValue(p, KmipDataType.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
                    builder.cryptographicAlgorithm(mapper.readValue(p, CryptographicAlgorithm.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_LENGTH ->
                    builder.cryptographicLength(mapper.readValue(p, CryptographicLength.class));
            case KmipTag.Standard.KEY_WRAPPING_DATA ->
                    builder.keyWrappingData(mapper.readValue(p, KeyWrappingData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected KeyBlock build(KeyBlock.KeyBlockBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return KeyBlock.encodingType;
    }
}