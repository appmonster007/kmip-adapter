package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Key;

import java.nio.ByteBuffer;

public class KeyXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Key, ByteBuffer> {

    public KeyXmlSerializer() {
        super(Key::getValue);
    }
}