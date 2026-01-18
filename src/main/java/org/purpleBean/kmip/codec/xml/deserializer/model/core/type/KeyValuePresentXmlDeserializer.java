package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;

public class KeyValuePresentXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyValuePresent, Boolean> {

    public KeyValuePresentXmlDeserializer() {
        super(KeyValuePresent.kmipTag, KeyValuePresent.encodingType, Boolean.class, value -> KeyValuePresent.builder().value(value).build());
    }
}