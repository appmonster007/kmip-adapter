package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueXmlDeserializer() {
        super(AsynchronousCorrelationValue.kmipTag, AsynchronousCorrelationValue.encodingType, ByteBuffer.class, value -> AsynchronousCorrelationValue.builder().value(value).build());
    }
}