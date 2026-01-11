package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageXmlDeserializer extends AbstractKmipXmlDeserializer<ResultMessage, String> {

    public ResultMessageXmlDeserializer() {
        super(ResultMessage.kmipTag, ResultMessage.encodingType, String.class, value -> ResultMessage.builder().value(value).build());
    }
}