package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.UnwrapMode;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class UnwrapModeXmlSerializer extends AbstractKmipXmlSerializer<UnwrapMode, String> {

    public UnwrapModeXmlSerializer() {
        super(UnwrapMode::getDescription);
    }
}