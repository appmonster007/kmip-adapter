package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class KeyValueLocationTypeXmlSerializer extends AbstractKmipXmlSerializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeXmlSerializer() {
        super(KeyValueLocationType::getDescription);
    }
}