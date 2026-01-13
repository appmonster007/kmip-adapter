package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ResultReason, Integer> {

    public ResultReasonTtlvDeserializer() {
        super(ResultReason.kmipTag, ResultReason.encodingType, Integer.class, value -> new ResultReason(ResultReason.fromValue(value)));
    }
}