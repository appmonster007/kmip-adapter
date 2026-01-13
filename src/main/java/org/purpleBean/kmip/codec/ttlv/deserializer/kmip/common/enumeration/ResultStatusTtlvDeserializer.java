package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ResultStatus, Integer> {

    public ResultStatusTtlvDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, Integer.class, value -> new ResultStatus(ResultStatus.fromValue(value)));
    }
}