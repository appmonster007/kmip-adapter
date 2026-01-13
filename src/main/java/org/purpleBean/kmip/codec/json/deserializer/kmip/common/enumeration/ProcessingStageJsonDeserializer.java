package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

public class ProcessingStageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProcessingStage, String> {

    public ProcessingStageJsonDeserializer() {
        super(ProcessingStage.kmipTag, ProcessingStage.encodingType, String.class, value -> new ProcessingStage(ProcessingStage.fromName(value)));
    }
}