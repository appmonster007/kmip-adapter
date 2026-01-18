package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringXmlSerializer() {
        super(KeyMaterialByteString::getValue);
    }
}