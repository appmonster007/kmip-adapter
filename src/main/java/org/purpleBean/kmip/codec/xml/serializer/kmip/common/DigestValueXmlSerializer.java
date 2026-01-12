package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class DigestValueXmlSerializer extends AbstractKmipXmlSerializer<DigestValue, ByteBuffer> {

    public DigestValueXmlSerializer() {
        super(DigestValue::getValue);
    }
}