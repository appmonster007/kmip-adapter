package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;

public class UnwrapModeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UnwrapMode, String> {

    public UnwrapModeXmlSerializer() {
        super(UnwrapMode::getDescription);
    }
}