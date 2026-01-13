package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<BatchErrorContinuationOption, Integer> {

    public BatchErrorContinuationOptionTtlvDeserializer() {
        super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType, Integer.class, value -> new BatchErrorContinuationOption(BatchErrorContinuationOption.fromValue(value)));
    }
}