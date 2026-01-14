package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ResultReason, Integer> {

    public ResultReasonTtlvSerializer() {
        super(ResultReason::getValue);
    }
}