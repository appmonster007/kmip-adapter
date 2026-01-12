package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

public class ProcessingStageJsonSerializer extends AbstractKmipJsonSerializer<ProcessingStage, String> {

    public ProcessingStageJsonSerializer() {
        super(ProcessingStage::getDescription);
    }
}