package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageTtlvSerializer extends AbstractKmipTtlvSerializer<ResultMessage, String> {

    public ResultMessageTtlvSerializer() {
        super(ResultMessage::getValue);
    }
}