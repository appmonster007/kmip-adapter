package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Username;

public class UsernameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Username, String> {

    public UsernameXmlDeserializer() {
        super(Username.kmipTag, Username.encodingType, String.class, value -> Username.builder().value(value).build());
    }
}