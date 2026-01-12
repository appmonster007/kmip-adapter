package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

public class AlternativeNameTypeXmlSerializer extends AbstractKmipXmlSerializer<AlternativeNameType, String> {

    public AlternativeNameTypeXmlSerializer() {
        super(AlternativeNameType::getDescription);
    }
}