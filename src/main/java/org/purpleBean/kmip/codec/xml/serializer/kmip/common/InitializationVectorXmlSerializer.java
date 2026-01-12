package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.InitializationVector;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class InitializationVectorXmlSerializer extends AbstractKmipXmlSerializer<InitializationVector, ByteBuffer> {

    public InitializationVectorXmlSerializer() {
        super(InitializationVector::getValue);
    }
}