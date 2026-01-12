package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataXmlSerializer extends AbstractKmipXmlSerializer<SignatureData, ByteBuffer> {

    public SignatureDataXmlSerializer() {
        super(SignatureData::getValue);
    }
}