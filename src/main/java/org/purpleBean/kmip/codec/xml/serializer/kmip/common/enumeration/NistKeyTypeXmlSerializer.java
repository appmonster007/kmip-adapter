package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

public class NistKeyTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NistKeyType, String> {

    public NistKeyTypeXmlSerializer() {
        super(NistKeyType::getDescription);
    }
}