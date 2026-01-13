package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<BatchErrorContinuationOption, Integer> {

    public BatchErrorContinuationOptionTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}