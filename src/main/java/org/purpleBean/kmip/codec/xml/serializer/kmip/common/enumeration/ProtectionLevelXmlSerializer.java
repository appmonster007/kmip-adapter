package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelXmlSerializer extends AbstractKmipXmlSerializer<ProtectionLevel, String> {

    public ProtectionLevelXmlSerializer() {
        super(ProtectionLevel::getDescription);
    }
}