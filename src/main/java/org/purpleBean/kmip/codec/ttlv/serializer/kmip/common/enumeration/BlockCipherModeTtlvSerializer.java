package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<BlockCipherMode, Integer> {

    public BlockCipherModeTtlvSerializer() {
        super(BlockCipherMode::getValue);
    }
}