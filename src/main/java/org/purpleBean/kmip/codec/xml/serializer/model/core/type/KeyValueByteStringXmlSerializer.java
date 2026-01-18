package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringXmlSerializer() {
        super(KeyValueByteString::getValue);
    }
}