package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AsynchronousCorrelationValue;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueXmlSerializer extends AbstractKmipXmlSerializer<AsynchronousCorrelationValue, ByteBuffer> {

    public AsynchronousCorrelationValueXmlSerializer() {
        super(AsynchronousCorrelationValue::getValue);
    }
}