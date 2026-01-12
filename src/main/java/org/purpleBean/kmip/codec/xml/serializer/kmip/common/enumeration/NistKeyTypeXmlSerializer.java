package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.NistKeyType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class NistKeyTypeXmlSerializer extends AbstractKmipXmlSerializer<NistKeyType, String> {

    public NistKeyTypeXmlSerializer() {
        super(NistKeyType::getDescription);
    }
}