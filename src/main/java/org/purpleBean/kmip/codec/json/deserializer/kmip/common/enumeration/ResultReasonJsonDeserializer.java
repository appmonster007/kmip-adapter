package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonJsonDeserializer extends AbstractKmipJsonDeserializer<ResultReason, String> {

    public ResultReasonJsonDeserializer() {
        super(ResultReason.kmipTag, ResultReason.encodingType, String.class, value -> new ResultReason(ResultReason.fromName(value)));
    }
}