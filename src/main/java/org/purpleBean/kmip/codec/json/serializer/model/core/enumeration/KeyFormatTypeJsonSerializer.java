package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;

public class KeyFormatTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyFormatType, String> {

    public KeyFormatTypeJsonSerializer() {
        super(KeyFormatType::getDescription);
    }
}