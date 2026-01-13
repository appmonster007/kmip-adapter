package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ResultReason, String> {

    public ResultReasonJsonSerializer() {
        super(ResultReason::getDescription);
    }
}