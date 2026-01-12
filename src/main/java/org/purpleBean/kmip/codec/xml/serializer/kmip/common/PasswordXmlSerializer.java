package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.Password;

public class PasswordXmlSerializer extends AbstractKmipXmlSerializer<Password, String> {

    public PasswordXmlSerializer() {
        super(Password::getValue);
    }
}