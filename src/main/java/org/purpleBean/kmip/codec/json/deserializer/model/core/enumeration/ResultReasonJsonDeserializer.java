package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;

public class ResultReasonJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ResultReason, String> {

    public ResultReasonJsonDeserializer() {
        super(ResultReason.kmipTag, ResultReason.encodingType, String.class, value -> ResultReason.fromName(value).inst());
    }
}