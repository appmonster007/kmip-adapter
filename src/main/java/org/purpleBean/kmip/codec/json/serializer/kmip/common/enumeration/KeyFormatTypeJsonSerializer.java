package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

public class KeyFormatTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyFormatType, String> {

    public KeyFormatTypeJsonSerializer() {
        super(KeyFormatType::getDescription);
    }
}