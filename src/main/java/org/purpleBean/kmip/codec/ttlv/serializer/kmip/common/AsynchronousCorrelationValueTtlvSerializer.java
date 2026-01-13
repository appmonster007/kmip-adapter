package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueTtlvSerializer() {
        super(AsynchronousCorrelationValue::getValue);
    }
}