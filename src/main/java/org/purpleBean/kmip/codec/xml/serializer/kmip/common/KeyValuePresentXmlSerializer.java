package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentXmlSerializer extends AbstractKmipXmlSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentXmlSerializer() {
        super(KeyValuePresent::getValue);
    }
}