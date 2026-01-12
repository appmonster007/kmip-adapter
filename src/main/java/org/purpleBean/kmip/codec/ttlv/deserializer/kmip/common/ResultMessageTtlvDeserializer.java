package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageTtlvDeserializer extends AbstractKmipTtlvDeserializer<ResultMessage, String> {

    public ResultMessageTtlvDeserializer() {
        super(ResultMessage.kmipTag, ResultMessage.encodingType, String.class, value -> ResultMessage.builder().value(value).build());
    }
}