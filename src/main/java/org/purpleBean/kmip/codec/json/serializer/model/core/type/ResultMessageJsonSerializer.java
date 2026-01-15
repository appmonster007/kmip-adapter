package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ResultMessage;

public class ResultMessageJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ResultMessage, String> {

    public ResultMessageJsonSerializer() {
        super(ResultMessage::getValue);
    }
}