package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ResultMessage, String> {

    public ResultMessageJsonDeserializer() {
        super(ResultMessage.kmipTag, ResultMessage.encodingType, String.class, value -> ResultMessage.builder().value(value).build());
    }
}