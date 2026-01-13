package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ResultMessage, String> {

    public ResultMessageTtlvSerializer() {
        super(ResultMessage::getValue);
    }
}