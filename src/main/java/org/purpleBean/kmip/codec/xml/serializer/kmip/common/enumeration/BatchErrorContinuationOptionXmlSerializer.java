package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionXmlSerializer() {
        super(BatchErrorContinuationOption::getDescription);
    }
}