package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueJsonSerializer() {
        super(AsynchronousCorrelationValue::getValue);
    }
}