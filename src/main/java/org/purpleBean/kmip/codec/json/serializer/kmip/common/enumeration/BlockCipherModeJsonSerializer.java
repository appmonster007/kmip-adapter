package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeJsonSerializer extends AbstractKmipJsonSerializer<BlockCipherMode, String> {

    public BlockCipherModeJsonSerializer() {
        super(BlockCipherMode::getDescription);
    }
}