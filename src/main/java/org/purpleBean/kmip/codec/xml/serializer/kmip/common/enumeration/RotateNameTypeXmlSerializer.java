package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RotateNameType, String> {

    public RotateNameTypeXmlSerializer() {
        super(RotateNameType::getDescription);
    }
}