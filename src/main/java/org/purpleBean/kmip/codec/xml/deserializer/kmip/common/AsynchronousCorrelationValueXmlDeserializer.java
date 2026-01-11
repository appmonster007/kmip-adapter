package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueXmlDeserializer extends AbstractKmipXmlDeserializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueXmlDeserializer() {
        super(AsynchronousCorrelationValue.kmipTag, AsynchronousCorrelationValue.encodingType, ByteBuffer.class, value -> AsynchronousCorrelationValue.builder().value(value).build());
    }
}