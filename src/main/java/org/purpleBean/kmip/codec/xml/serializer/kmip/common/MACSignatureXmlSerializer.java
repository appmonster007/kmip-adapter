package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureXmlSerializer extends AbstractKmipXmlSerializer<MACSignature, ByteBuffer> {

    public MACSignatureXmlSerializer() {
        super(MACSignature::getValue);
    }
}