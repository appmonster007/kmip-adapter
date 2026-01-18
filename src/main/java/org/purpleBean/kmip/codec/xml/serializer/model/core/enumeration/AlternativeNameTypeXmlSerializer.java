package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AlternativeNameType, String> {

    public AlternativeNameTypeXmlSerializer() {
        super(AlternativeNameType::getDescription);
    }
}