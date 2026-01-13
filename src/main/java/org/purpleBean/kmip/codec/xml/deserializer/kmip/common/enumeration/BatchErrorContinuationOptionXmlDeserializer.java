package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionXmlDeserializer() {
        super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType, String.class, value -> new BatchErrorContinuationOption(BatchErrorContinuationOption.fromName(value)));
    }
}