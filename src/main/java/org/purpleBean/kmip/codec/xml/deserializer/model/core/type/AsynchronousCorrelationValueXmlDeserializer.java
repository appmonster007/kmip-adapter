package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AsynchronousCorrelationValue, AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder> {

    public AsynchronousCorrelationValueXmlDeserializer() {
        super(AsynchronousCorrelationValue.kmipTag, AsynchronousCorrelationValue.encodingType);
    }

    @Override
    protected AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder createBuilder() {
        return AsynchronousCorrelationValue.builder();
    }

    @Override
    protected void setValue(AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected AsynchronousCorrelationValue build(AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder builder) {
        return builder.build();
    }
}