package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;

import java.io.IOException;

public class SplitKeyMethodJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SplitKeyMethod, SplitKeyMethod.SplitKeyMethodBuilder> {

    public SplitKeyMethodJsonDeserializer() {
        super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType);
    }

    @Override
    protected SplitKeyMethod.SplitKeyMethodBuilder createBuilder() {
        return SplitKeyMethod.builder();
    }

    @Override
    protected void setValue(SplitKeyMethod.SplitKeyMethodBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(SplitKeyMethod.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected SplitKeyMethod build(SplitKeyMethod.SplitKeyMethodBuilder builder) {
        return builder.build();
    }
}
