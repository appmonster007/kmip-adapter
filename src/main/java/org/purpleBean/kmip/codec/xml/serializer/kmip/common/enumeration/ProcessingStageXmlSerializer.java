package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ProcessingStage;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ProcessingStageXmlSerializer extends AbstractKmipXmlSerializer<ProcessingStage, String> {

    public ProcessingStageXmlSerializer() {
        super(ProcessingStage::getDescription);
    }
}