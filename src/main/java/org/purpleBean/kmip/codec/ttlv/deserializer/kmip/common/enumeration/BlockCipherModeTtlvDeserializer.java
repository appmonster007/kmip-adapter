package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeTtlvDeserializer extends AbstractKmipTtlvDeserializer<BlockCipherMode, Integer> {

    public BlockCipherModeTtlvDeserializer() {
        super(BlockCipherMode.kmipTag, BlockCipherMode.encodingType, Integer.class, value -> new BlockCipherMode(BlockCipherMode.fromValue(value)));
    }
}