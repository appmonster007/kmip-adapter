package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionJsonDeserializer() {
        super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType, String.class, value -> BatchErrorContinuationOption.fromName(value).inst());
    }
}