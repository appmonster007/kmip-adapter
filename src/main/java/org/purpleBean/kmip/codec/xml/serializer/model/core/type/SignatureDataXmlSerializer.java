package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SignatureData, ByteBuffer> {

    public SignatureDataXmlSerializer() {
        super(SignatureData::getValue);
    }
}