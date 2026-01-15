package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.Password;

public class PasswordXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Password, String> {

    public PasswordXmlSerializer() {
        super(Password::getValue);
    }
}