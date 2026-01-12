package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueXmlSerializer extends AbstractKmipXmlSerializer<DigestValue, ByteBuffer> {

    public DigestValueXmlSerializer() {
        super(DigestValue::getValue);
    }
}