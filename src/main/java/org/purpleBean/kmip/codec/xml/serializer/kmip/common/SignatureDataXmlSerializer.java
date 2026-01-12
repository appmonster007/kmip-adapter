package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import java.nio.ByteBuffer;
import org.purpleBean.kmip.common.SignatureData;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class SignatureDataXmlSerializer extends AbstractKmipXmlSerializer<SignatureData, ByteBuffer> {

    public SignatureDataXmlSerializer() {
        super(SignatureData::getValue);
    }
}