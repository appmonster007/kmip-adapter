package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;

import java.io.IOException;

public class SplitKeyThresholdJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SplitKeyThreshold, SplitKeyThreshold.SplitKeyThresholdBuilder> {

    public SplitKeyThresholdJsonDeserializer() {
        super(SplitKeyThreshold.kmipTag, SplitKeyThreshold.encodingType);
    }

    @Override
    protected SplitKeyThreshold.SplitKeyThresholdBuilder createBuilder() {
        return SplitKeyThreshold.builder();
    }

    @Override
    protected void setValue(SplitKeyThreshold.SplitKeyThresholdBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected SplitKeyThreshold build(SplitKeyThreshold.SplitKeyThresholdBuilder builder) {
        return builder.build();
    }
}
