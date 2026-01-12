package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusXmlSerializer extends AbstractKmipXmlSerializer<ResultStatus, String> {

    public ResultStatusXmlSerializer() {
        super(ResultStatus::getDescription);
    }
}