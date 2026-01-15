package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;

public class BlockCipherModeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<BlockCipherMode, String> {

    public BlockCipherModeXmlDeserializer() {
        super(BlockCipherMode.kmipTag, BlockCipherMode.encodingType, String.class, value -> new BlockCipherMode(BlockCipherMode.fromName(value)));
    }
}