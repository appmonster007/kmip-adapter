package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentXmlSerializer() {
        super(KeyValuePresent::getValue);
    }
}