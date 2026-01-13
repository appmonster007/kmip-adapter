package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeXmlSerializer() {
        super(KeyValueLocationType::getDescription);
    }
}