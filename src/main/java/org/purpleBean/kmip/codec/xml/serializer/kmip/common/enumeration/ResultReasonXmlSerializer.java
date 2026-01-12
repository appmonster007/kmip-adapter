package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ResultReason;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ResultReasonXmlSerializer extends AbstractKmipXmlSerializer<ResultReason, String> {

    public ResultReasonXmlSerializer() {
        super(ResultReason::getDescription);
    }
}