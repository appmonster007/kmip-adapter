package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;

public class RotateNameTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RotateNameType, String> {

    public RotateNameTypeJsonSerializer() {
        super(RotateNameType::getDescription);
    }
}