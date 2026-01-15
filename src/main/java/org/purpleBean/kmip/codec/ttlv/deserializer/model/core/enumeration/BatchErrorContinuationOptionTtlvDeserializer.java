package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<BatchErrorContinuationOption, Integer> {

    public BatchErrorContinuationOptionTtlvDeserializer() {
        super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType, Integer.class, value -> new BatchErrorContinuationOption(BatchErrorContinuationOption.fromValue(value)));
    }
}