package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeJsonSerializer() {
        super(KeyValueLocationType::getDescription);
    }
}