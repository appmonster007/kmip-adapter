package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelJsonSerializer extends AbstractKmipJsonSerializer<ProtectionLevel, String> {

    public ProtectionLevelJsonSerializer() {
        super(ProtectionLevel::getDescription);
    }
}