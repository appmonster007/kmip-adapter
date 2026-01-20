package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;

public class ProcessingStageXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProcessingStage, String> {

    public ProcessingStageXmlDeserializer() {
        super(ProcessingStage.kmipTag, ProcessingStage.encodingType, String.class, value -> ProcessingStage.fromName(value).inst());
    }
}