package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ProcessingStage;

public class ProcessingStageTtlvSerializer extends AbstractKmipTtlvSerializer<ProcessingStage, Integer> {

    public ProcessingStageTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}