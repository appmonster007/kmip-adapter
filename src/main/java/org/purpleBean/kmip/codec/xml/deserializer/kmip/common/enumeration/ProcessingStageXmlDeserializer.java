package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

public class ProcessingStageXmlDeserializer extends AbstractKmipXmlDeserializer<ProcessingStage, String> {

    public ProcessingStageXmlDeserializer() {
        super(ProcessingStage.kmipTag, ProcessingStage.encodingType, String.class, value -> new ProcessingStage(ProcessingStage.fromName(value)));
    }
}