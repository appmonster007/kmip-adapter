package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueJsonSerializer() {
        super(AsynchronousCorrelationValue::getValue);
    }
}