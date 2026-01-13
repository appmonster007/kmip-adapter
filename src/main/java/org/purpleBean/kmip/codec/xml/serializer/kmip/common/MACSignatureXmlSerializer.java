package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MACSignature, ByteBuffer> {

    public MACSignatureXmlSerializer() {
        super(MACSignature::getValue);
    }
}