package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;

public class RotateNameTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RotateNameType, String> {

    public RotateNameTypeXmlSerializer() {
        super(RotateNameType::getDescription);
    }
}