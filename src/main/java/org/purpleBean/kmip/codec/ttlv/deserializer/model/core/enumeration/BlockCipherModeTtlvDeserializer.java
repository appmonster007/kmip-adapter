package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;

public class BlockCipherModeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<BlockCipherMode, Integer> {

    public BlockCipherModeTtlvDeserializer() {
        super(BlockCipherMode.kmipTag, BlockCipherMode.encodingType, Integer.class, value -> new BlockCipherMode(BlockCipherMode.fromValue(value)));
    }
}