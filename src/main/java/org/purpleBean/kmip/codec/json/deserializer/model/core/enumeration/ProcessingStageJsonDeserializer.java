package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;

public class ProcessingStageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProcessingStage, String> {

    public ProcessingStageJsonDeserializer() {
        super(ProcessingStage.kmipTag, ProcessingStage.encodingType, String.class, value -> ProcessingStage.fromName(value).inst());
    }
}