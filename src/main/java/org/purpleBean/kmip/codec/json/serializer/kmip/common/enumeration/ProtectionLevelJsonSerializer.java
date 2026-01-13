package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProtectionLevel, String> {

    public ProtectionLevelJsonSerializer() {
        super(ProtectionLevel::getDescription);
    }
}