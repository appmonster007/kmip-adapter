package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusXmlDeserializer extends AbstractKmipXmlDeserializer<ResultStatus, String> {

    public ResultStatusXmlDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, String.class, value -> new ResultStatus(ResultStatus.fromName(value)));
    }
}