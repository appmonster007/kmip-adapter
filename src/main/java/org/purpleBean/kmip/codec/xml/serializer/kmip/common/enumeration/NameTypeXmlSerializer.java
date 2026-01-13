package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NameType, String> {

    public NameTypeXmlSerializer() {
        super(NameType::getDescription);
    }
}