package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionJsonSerializer() {
        super(BatchErrorContinuationOption::getDescription);
    }
}