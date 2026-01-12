package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusTtlvSerializer extends AbstractKmipTtlvSerializer<ResultStatus, Integer> {

    public ResultStatusTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}