package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ResultStatus, String> {

    public ResultStatusJsonDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, String.class, value -> new ResultStatus(ResultStatus.fromName(value)));
    }
}