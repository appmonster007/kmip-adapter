package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousIndicator;

import java.io.IOException;

public class AsynchronousIndicatorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AsynchronousIndicator, AsynchronousIndicator.AsynchronousIndicatorBuilder> {

    public AsynchronousIndicatorJsonDeserializer() {
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
