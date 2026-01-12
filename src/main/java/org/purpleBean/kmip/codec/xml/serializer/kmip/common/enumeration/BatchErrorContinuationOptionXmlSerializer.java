package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

public class BatchErrorContinuationOptionXmlSerializer extends AbstractKmipXmlSerializer<BatchErrorContinuationOption, String> {

    public BatchErrorContinuationOptionXmlSerializer() {
        super(BatchErrorContinuationOption::getDescription);
    }
}