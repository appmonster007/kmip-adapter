package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueJsonDeserializer() {
        super(AsynchronousCorrelationValue.kmipTag, AsynchronousCorrelationValue.encodingType, ByteBuffer.class, value -> AsynchronousCorrelationValue.builder().value(value).build());
    }
}