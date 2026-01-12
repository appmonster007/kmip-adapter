package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

public class ProcessingStageXmlSerializer extends AbstractKmipXmlSerializer<ProcessingStage, String> {

    public ProcessingStageXmlSerializer() {
        super(ProcessingStage::getDescription);
    }
}