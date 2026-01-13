package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringXmlSerializer() {
        super(KeyMaterialByteString::getValue);
    }
}