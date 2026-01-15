package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;

public class ResultStatusJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ResultStatus, String> {

    public ResultStatusJsonSerializer() {
        super(ResultStatus::getDescription);
    }
}