package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueTtlvSerializer extends AbstractKmipTtlvSerializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueTtlvSerializer() {
        super(AsynchronousCorrelationValue::getValue);
    }
}