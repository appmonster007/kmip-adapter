package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.Salt;

import java.nio.ByteBuffer;

public class SaltXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Salt, ByteBuffer> {

    public SaltXmlSerializer() {
        super(Salt::getValue);
    }
}