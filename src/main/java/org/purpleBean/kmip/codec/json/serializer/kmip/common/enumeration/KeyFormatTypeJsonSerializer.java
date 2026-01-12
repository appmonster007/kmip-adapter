package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeJsonSerializer extends AbstractKmipJsonSerializer<KeyFormatType, String> {

    public KeyFormatTypeJsonSerializer() {
        super(KeyFormatType::getDescription);
    }
}