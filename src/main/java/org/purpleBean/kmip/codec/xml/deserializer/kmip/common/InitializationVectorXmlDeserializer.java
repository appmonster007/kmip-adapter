package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.InitializationVector;

import java.nio.ByteBuffer;


public class InitializationVectorXmlDeserializer extends AbstractKmipXmlDeserializer<InitializationVector, ByteBuffer> {

    public InitializationVectorXmlDeserializer() {
        super(InitializationVector.kmipTag, InitializationVector.encodingType, ByteBuffer.class, value -> InitializationVector.builder().value(value).build());
    }
}