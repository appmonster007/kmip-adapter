package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.Salt;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class SaltXmlSerializer extends AbstractKmipXmlSerializer<Salt, ByteBuffer> {

    public SaltXmlSerializer() {
        super(Salt::getValue);
    }
}