package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class NameTypeXmlSerializer extends AbstractKmipXmlSerializer<NameType, String> {

    public NameTypeXmlSerializer() {
        super(NameType::getDescription);
    }
}