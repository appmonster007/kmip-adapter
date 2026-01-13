package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ResultReason, String> {

    public ResultReasonXmlSerializer() {
        super(ResultReason::getDescription);
    }
}