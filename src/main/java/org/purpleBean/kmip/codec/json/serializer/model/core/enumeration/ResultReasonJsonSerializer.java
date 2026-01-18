package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;

public class ResultReasonJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ResultReason, String> {

    public ResultReasonJsonSerializer() {
        super(ResultReason::getDescription);
    }
}