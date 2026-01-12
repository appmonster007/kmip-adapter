package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonTtlvSerializer extends AbstractKmipTtlvSerializer<ResultReason, Integer> {

    public ResultReasonTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}