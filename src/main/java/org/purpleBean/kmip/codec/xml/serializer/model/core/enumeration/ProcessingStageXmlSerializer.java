package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;

public class ProcessingStageXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProcessingStage, String> {

    public ProcessingStageXmlSerializer() {
        super(ProcessingStage::getDescription);
    }
}