package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ResultMessage, String> {

    public ResultMessageTtlvDeserializer() {
        super(ResultMessage.kmipTag, ResultMessage.encodingType, String.class, value -> ResultMessage.builder().value(value).build());
    }
}