package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DigestValue, ByteBuffer> {

    public DigestValueXmlSerializer() {
        super(DigestValue::getValue);
    }
}