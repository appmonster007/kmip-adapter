package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.NameType;

public class NameTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NameType, String> {

    public NameTypeXmlSerializer() {
        super(NameType::getDescription);
    }
}