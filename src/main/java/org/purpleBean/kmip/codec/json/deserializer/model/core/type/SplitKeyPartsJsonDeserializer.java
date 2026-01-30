package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;

import java.io.IOException;

public class SplitKeyPartsJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SplitKeyParts, SplitKeyParts.SplitKeyPartsBuilder> {

    public SplitKeyPartsJsonDeserializer() {
        super(SplitKeyParts.kmipTag, SplitKeyParts.encodingType);
    }

    @Override
    protected SplitKeyParts.SplitKeyPartsBuilder createBuilder() {
        return SplitKeyParts.builder();
    }

    @Override
    protected void setValue(SplitKeyParts.SplitKeyPartsBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected SplitKeyParts build(SplitKeyParts.SplitKeyPartsBuilder builder) {
        return builder.build();
    }
}
