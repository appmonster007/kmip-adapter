package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;

public class ResultStatusTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ResultStatus, Integer> {

    public ResultStatusTtlvDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, Integer.class, value -> new ResultStatus(ResultStatus.fromValue(value)));
    }
}