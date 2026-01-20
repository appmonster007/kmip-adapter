package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;

public class ResultStatusXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ResultStatus, String> {

    public ResultStatusXmlDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, String.class, value -> ResultStatus.fromName(value).inst());
    }
}