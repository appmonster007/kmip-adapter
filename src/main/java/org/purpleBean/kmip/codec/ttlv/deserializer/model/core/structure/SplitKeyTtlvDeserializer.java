package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SplitKey;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SplitKeyTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SplitKey, SplitKey.SplitKeyBuilder> {

    public SplitKeyTtlvDeserializer() {
        super(SplitKey.kmipTag);
    }

    @Override
    protected SplitKey.SplitKeyBuilder createBuilder() {
        return SplitKey.builder();
    }

    @Override
    protected void setValue(SplitKey.SplitKeyBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.SPLIT_KEY_PARTS -> builder.splitKeyParts(mapper.readValue(p, SplitKeyParts.class));
            case KmipTag.Standard.KEY_PART_IDENTIFIER ->
                    builder.keyPartIdentifier(mapper.readValue(p, KeyPartIdentifier.class));
            case KmipTag.Standard.SPLIT_KEY_THRESHOLD ->
                    builder.splitKeyThreshold(mapper.readValue(p, SplitKeyThreshold.class));
            case KmipTag.Standard.SPLIT_KEY_METHOD -> builder.splitKeyMethod(mapper.readValue(p, SplitKeyMethod.class));
            case KmipTag.Standard.PRIME_FIELD_SIZE -> builder.primeFieldSize(mapper.readValue(p, PrimeFieldSize.class));
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SplitKey build(SplitKey.SplitKeyBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SplitKey.encodingType;
    }
}