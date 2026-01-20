package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;

public class ResultStatusJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ResultStatus, String> {

    public ResultStatusJsonDeserializer() {
        super(ResultStatus.kmipTag, ResultStatus.encodingType, String.class, value -> ResultStatus.fromName(value).inst());
    }
}