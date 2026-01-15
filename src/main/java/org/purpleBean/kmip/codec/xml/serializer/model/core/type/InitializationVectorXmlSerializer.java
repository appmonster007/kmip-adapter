package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InitializationVector, ByteBuffer> {

    public InitializationVectorXmlSerializer() {
        super(InitializationVector::getValue);
    }
}