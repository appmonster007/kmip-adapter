package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageJsonSerializer extends AbstractKmipJsonSerializer<ResultMessage, String> {

    public ResultMessageJsonSerializer() {
        super(ResultMessage::getValue);
    }
}