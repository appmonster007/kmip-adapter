package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueXmlSerializer() {
        super(AsynchronousCorrelationValue::getValue);
    }
}