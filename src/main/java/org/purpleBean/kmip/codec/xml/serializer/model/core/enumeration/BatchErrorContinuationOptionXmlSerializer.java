package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionXmlSerializer() {
        super(BatchErrorContinuationOption::getDescription);
    }
}