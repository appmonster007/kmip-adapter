package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.MACSignature;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class MACSignatureXmlSerializer extends AbstractKmipXmlSerializer<MACSignature, ByteBuffer> {

    public MACSignatureXmlSerializer() {
        super(MACSignature::getValue);
    }
}