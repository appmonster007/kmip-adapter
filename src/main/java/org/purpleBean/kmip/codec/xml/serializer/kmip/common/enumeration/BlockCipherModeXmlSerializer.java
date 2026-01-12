package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.BlockCipherMode;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class BlockCipherModeXmlSerializer extends AbstractKmipXmlSerializer<BlockCipherMode, String> {

    public BlockCipherModeXmlSerializer() {
        super(BlockCipherMode::getDescription);
    }
}