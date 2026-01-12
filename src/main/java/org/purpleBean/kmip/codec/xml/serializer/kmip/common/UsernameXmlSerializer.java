package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.Username;

public class UsernameXmlSerializer extends AbstractKmipXmlSerializer<Username, String> {

    public UsernameXmlSerializer() {
        super(Username::getValue);
    }
}