package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueXmlSerializer() {
        super(KeyValueLocationValue::getValue);
    }
}