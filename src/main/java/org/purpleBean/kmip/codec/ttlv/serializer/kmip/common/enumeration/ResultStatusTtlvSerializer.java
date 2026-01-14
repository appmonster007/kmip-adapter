package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ResultStatus, Integer> {

    public ResultStatusTtlvSerializer() {
        super(ResultStatus::getValue);
    }
}