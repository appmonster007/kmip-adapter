package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeXmlSerializer() {
        super(KeyValueLocationType::getDescription);
    }
}