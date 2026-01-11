package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusJsonDeserializer extends AbstractKmipJsonDeserializer<ResultStatus, String> {

    public ResultStatusJsonDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, String.class, value -> new ResultStatus(ResultStatus.fromName(value)));
    }
}