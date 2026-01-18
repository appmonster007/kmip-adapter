package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueTtlvSerializer() {
        super(AsynchronousCorrelationValue::getValue);
    }
}