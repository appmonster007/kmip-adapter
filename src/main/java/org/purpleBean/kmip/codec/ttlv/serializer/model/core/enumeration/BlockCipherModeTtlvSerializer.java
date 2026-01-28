package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;

public class BlockCipherModeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<BlockCipherMode, Integer> {

    public BlockCipherModeTtlvSerializer() {
        super(BlockCipherMode::getIntValue);
    }
}