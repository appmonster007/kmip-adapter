package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;

public class ProtectionLevelXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProtectionLevel, String> {

    public ProtectionLevelXmlSerializer() {
        super(ProtectionLevel::getDescription);
    }
}