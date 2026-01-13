package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ResultStatus, String> {

    public ResultStatusJsonSerializer() {
        super(ResultStatus::getDescription);
    }
}