package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousIndicator;

import java.io.IOException;

public class AsynchronousIndicatorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AsynchronousIndicator, AsynchronousIndicator.AsynchronousIndicatorBuilder> {

    public AsynchronousIndicatorXmlDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType);
    }

    @Override
    protected AsynchronousIndicator.AsynchronousIndicatorBuilder createBuilder() {
        return AsynchronousIndicator.builder();
    }

    @Override
    protected void setValue(AsynchronousIndicator.AsynchronousIndicatorBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected AsynchronousIndicator build(AsynchronousIndicator.AsynchronousIndicatorBuilder builder) {
        return builder.build();
    }
}