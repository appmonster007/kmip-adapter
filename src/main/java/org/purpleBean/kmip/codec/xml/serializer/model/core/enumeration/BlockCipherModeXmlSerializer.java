package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;

public class BlockCipherModeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<BlockCipherMode, String> {

    public BlockCipherModeXmlSerializer() {
        super(BlockCipherMode::getDescription);
    }
}