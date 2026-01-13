package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ResultMessage, String> {

    public ResultMessageXmlDeserializer() {
        super(ResultMessage.kmipTag, ResultMessage.encodingType, String.class, value -> ResultMessage.builder().value(value).build());
    }
}