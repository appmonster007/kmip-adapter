package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

import java.io.IOException;

public class DrbgAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DrbgAlgorithm, DrbgAlgorithm.DrbgAlgorithmBuilder> {

    public DrbgAlgorithmJsonDeserializer() {
        super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType);
    }

    @Override
    protected DrbgAlgorithm.DrbgAlgorithmBuilder createBuilder() {
        return DrbgAlgorithm.builder();
    }

    @Override
    protected void setValue(DrbgAlgorithm.DrbgAlgorithmBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(DrbgAlgorithm.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected DrbgAlgorithm build(DrbgAlgorithm.DrbgAlgorithmBuilder builder) {
        return builder.build();
    }
}
