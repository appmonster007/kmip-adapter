package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonJsonSerializer extends AbstractKmipJsonSerializer<ResultReason, String> {

    public ResultReasonJsonSerializer() {
        super(ResultReason::getDescription);
    }
}