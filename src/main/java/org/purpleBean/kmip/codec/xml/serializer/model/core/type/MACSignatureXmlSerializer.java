package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MACSignature, ByteBuffer> {

    public MACSignatureXmlSerializer() {
        super(MACSignature::getValue);
    }
}