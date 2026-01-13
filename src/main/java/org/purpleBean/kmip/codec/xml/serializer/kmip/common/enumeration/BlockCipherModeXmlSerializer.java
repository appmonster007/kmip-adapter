package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

public class BlockCipherModeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<BlockCipherMode, String> {

    public BlockCipherModeXmlSerializer() {
        super(BlockCipherMode::getDescription);
    }
}