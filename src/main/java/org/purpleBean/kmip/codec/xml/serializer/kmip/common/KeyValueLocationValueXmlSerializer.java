package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueXmlSerializer extends AbstractKmipXmlSerializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueXmlSerializer() {
        super(KeyValueLocationValue::getValue);
    }
}