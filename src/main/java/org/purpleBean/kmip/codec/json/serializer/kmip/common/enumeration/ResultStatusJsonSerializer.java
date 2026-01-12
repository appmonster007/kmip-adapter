package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusJsonSerializer extends AbstractKmipJsonSerializer<ResultStatus, String> {

    public ResultStatusJsonSerializer() {
        super(ResultStatus::getDescription);
    }
}