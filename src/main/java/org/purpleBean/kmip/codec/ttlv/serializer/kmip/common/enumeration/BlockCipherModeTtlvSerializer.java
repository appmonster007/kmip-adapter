package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeTtlvSerializer extends AbstractKmipTtlvSerializer<BlockCipherMode, Integer> {

    public BlockCipherModeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}