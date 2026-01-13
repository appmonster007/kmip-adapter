package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<BlockCipherMode, String> {

    public BlockCipherModeJsonSerializer() {
        super(BlockCipherMode::getDescription);
    }
}