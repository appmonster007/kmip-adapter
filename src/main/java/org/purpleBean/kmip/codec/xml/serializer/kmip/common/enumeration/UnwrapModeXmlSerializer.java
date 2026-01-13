package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UnwrapMode, String> {

    public UnwrapModeXmlSerializer() {
        super(UnwrapMode::getDescription);
    }
}