package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RotateNameType, String> {

    public RotateNameTypeJsonSerializer() {
        super(RotateNameType::getDescription);
    }
}