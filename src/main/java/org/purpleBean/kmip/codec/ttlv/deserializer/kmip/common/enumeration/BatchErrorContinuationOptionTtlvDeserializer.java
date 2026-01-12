package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionTtlvDeserializer extends AbstractKmipTtlvDeserializer<BatchErrorContinuationOption, Integer> {

    public BatchErrorContinuationOptionTtlvDeserializer() {
        super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType, Integer.class, value -> new BatchErrorContinuationOption(BatchErrorContinuationOption.fromValue(value)));
    }
}