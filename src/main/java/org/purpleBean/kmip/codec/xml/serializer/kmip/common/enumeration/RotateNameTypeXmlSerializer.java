package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeXmlSerializer extends AbstractKmipXmlSerializer<RotateNameType, String> {

    public RotateNameTypeXmlSerializer() {
        super(RotateNameType::getDescription);
    }
}