package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ResultStatus, String> {

    public ResultStatusXmlDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, String.class, value -> new ResultStatus(ResultStatus.fromName(value)));
    }
}