package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionJsonSerializer() {
        super(BatchErrorContinuationOption::getDescription);
    }
}