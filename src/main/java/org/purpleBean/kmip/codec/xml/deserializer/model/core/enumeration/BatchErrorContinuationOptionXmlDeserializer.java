package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionXmlDeserializer() {
        super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType, String.class, value -> BatchErrorContinuationOption.fromName(value).inst());
    }
}