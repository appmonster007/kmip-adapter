package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

public class ProcessingStageTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProcessingStage, Integer> {

    public ProcessingStageTtlvSerializer() {
        super(ProcessingStage::getValue);
    }
}