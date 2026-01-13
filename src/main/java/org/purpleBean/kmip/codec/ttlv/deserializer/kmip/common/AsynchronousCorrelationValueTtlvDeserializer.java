package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueTtlvDeserializer() {
        super(AsynchronousCorrelationValue.kmipTag, AsynchronousCorrelationValue.encodingType, ByteBuffer.class, value -> AsynchronousCorrelationValue.builder().value(value).build());
    }
}