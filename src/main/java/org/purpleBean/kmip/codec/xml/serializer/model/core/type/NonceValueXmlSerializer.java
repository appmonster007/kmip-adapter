package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.nio.ByteBuffer;

public class NonceValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NonceValue, ByteBuffer> {

    public NonceValueXmlSerializer() {
        super(NonceValue::getValue);
    }
}