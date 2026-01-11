package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionJsonDeserializer extends AbstractKmipJsonDeserializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionJsonDeserializer() {
        super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType, String.class, value -> new BatchErrorContinuationOption(BatchErrorContinuationOption.fromName(value)));
    }
}