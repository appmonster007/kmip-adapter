package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.ProcessingStage;

import java.io.IOException;

public class ProcessingStageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProcessingStage, ProcessingStage.ProcessingStageBuilder> {

    public ProcessingStageJsonDeserializer() {
        super(ProcessingStage.kmipTag, ProcessingStage.encodingType);
    }

    @Override
    protected ProcessingStage.ProcessingStageBuilder createBuilder() {
        return ProcessingStage.builder();
    }

    @Override
    protected void setValue(ProcessingStage.ProcessingStageBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ProcessingStage.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ProcessingStage build(ProcessingStage.ProcessingStageBuilder builder) {
        return builder.build();
    }
}
