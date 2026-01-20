package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;

public class ProcessingStageTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProcessingStage, Integer> {

    public ProcessingStageTtlvDeserializer() {
        super(ProcessingStage.kmipTag, ProcessingStage.encodingType, Integer.class, value -> ProcessingStage.fromValue(value).inst());
    }
}