package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

public class KeyValueLocationValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueXmlSerializer() {
        super(KeyValueLocationValue::getValue);
    }
}