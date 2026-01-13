package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

public class ProtectionLevelXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProtectionLevel, String> {

    public ProtectionLevelXmlSerializer() {
        super(ProtectionLevel::getDescription);
    }
}