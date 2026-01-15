package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;

public class NistKeyTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NistKeyType, String> {

    public NistKeyTypeXmlSerializer() {
        super(NistKeyType::getDescription);
    }
}