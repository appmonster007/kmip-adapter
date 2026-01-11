package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.Username;

public class UsernameXmlDeserializer extends AbstractKmipXmlDeserializer<Username, String> {

    public UsernameXmlDeserializer() {
        super(Username.kmipTag, Username.encodingType, String.class, value -> Username.builder().value(value).build());
    }
}