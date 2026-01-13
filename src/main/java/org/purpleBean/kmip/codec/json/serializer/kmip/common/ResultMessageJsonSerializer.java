package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ResultMessage, String> {

    public ResultMessageJsonSerializer() {
        super(ResultMessage::getValue);
    }
}