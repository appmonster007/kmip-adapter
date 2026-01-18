package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;

public class ProcessingStageTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProcessingStage, Integer> {

    public ProcessingStageTtlvSerializer() {
        super(ProcessingStage::getValue);
    }
}