package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

public class ProcessingStageTtlvDeserializer extends AbstractKmipTtlvDeserializer<ProcessingStage, Integer> {

    public ProcessingStageTtlvDeserializer() {
        super(ProcessingStage.kmipTag, ProcessingStage.encodingType, Integer.class, value -> new ProcessingStage(ProcessingStage.fromValue(value)));
    }
}