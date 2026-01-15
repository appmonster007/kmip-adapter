package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;

public class KeyValuePresentXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentXmlSerializer() {
        super(KeyValuePresent::getValue);
    }
}