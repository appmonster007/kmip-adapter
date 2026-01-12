package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.Username;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class UsernameXmlSerializer extends AbstractKmipXmlSerializer<Username, String> {

    public UsernameXmlSerializer() {
        super(Username::getValue);
    }
}