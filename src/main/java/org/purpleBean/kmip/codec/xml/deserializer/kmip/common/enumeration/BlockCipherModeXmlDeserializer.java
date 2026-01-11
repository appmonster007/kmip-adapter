package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeXmlDeserializer extends AbstractKmipXmlDeserializer<BlockCipherMode, String> {

    public BlockCipherModeXmlDeserializer() {
        super(BlockCipherMode.kmipTag, BlockCipherMode.encodingType, String.class, value -> new BlockCipherMode(BlockCipherMode.fromName(value)));
    }
}