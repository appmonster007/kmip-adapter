package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeJsonSerializer() {
        super(KeyValueLocationType::getDescription);
    }
}