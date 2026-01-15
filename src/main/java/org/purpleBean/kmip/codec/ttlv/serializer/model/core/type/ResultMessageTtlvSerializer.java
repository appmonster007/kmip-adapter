package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ResultMessage;

public class ResultMessageTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ResultMessage, String> {

    public ResultMessageTtlvSerializer() {
        super(ResultMessage::getValue);
    }
}