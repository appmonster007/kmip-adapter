package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.KeyValuePresent;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class KeyValuePresentXmlSerializer extends AbstractKmipXmlSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentXmlSerializer() {
        super(KeyValuePresent::getValue);
    }
}