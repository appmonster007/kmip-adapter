package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.NonceId;

import java.nio.ByteBuffer;

public class NonceIdXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NonceId, ByteBuffer> {

    public NonceIdXmlSerializer() {
        super(NonceId::getValue);
    }
}