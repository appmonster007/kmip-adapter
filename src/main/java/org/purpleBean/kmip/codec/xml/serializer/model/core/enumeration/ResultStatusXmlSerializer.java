package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;

public class ResultStatusXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ResultStatus, String> {

    public ResultStatusXmlSerializer() {
        super(ResultStatus::getDescription);
    }
}