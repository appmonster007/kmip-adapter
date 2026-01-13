package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueXmlSerializer() {
        super(AsynchronousCorrelationValue::getValue);
    }
}