package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;

public class ProtectionLevelJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtectionLevel, String> {

    public ProtectionLevelJsonSerializer() {
        super(ProtectionLevel::getDescription);
    }
}