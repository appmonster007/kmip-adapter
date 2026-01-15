package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.nio.ByteBuffer;


public class InitializationVectorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InitializationVector, ByteBuffer> {

    public InitializationVectorXmlDeserializer() {
        super(InitializationVector.kmipTag, InitializationVector.encodingType, ByteBuffer.class, value -> InitializationVector.builder().value(value).build());
    }
}