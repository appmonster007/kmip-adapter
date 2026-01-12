package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ResultStatus;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ResultStatusXmlSerializer extends AbstractKmipXmlSerializer<ResultStatus, String> {

    public ResultStatusXmlSerializer() {
        super(ResultStatus::getDescription);
    }
}