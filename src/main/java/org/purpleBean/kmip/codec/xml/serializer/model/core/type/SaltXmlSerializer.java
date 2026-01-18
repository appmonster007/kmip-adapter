package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Salt;

import java.nio.ByteBuffer;

public class SaltXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Salt, ByteBuffer> {

    public SaltXmlSerializer() {
        super(Salt::getValue);
    }
}