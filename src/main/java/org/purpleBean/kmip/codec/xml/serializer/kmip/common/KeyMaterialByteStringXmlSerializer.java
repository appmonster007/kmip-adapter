package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.KeyMaterialByteString;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringXmlSerializer extends AbstractKmipXmlSerializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringXmlSerializer() {
        super(KeyMaterialByteString::getValue);
    }
}