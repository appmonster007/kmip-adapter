package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InitializationVector, ByteBuffer> {

    public InitializationVectorXmlSerializer() {
        super(InitializationVector::getValue);
    }
}