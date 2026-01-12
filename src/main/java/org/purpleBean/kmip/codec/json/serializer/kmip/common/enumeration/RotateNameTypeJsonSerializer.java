package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeJsonSerializer extends AbstractKmipJsonSerializer<RotateNameType, String> {

    public RotateNameTypeJsonSerializer() {
        super(RotateNameType::getDescription);
    }
}