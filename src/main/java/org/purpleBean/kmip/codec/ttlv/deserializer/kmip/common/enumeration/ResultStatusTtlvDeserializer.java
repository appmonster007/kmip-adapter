package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusTtlvDeserializer extends AbstractKmipTtlvDeserializer<ResultStatus, Integer> {

    public ResultStatusTtlvDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, Integer.class, value -> new ResultStatus(ResultStatus.fromValue(value)));
    }
}