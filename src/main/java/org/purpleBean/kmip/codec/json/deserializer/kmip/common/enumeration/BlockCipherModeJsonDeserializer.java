package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<BlockCipherMode, String> {

    public BlockCipherModeJsonDeserializer() {
        super(BlockCipherMode.kmipTag, BlockCipherMode.encodingType, String.class, value -> new BlockCipherMode(BlockCipherMode.fromName(value)));
    }
}