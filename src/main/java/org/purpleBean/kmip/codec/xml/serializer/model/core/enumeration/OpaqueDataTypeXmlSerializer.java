package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

public class OpaqueDataTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<OpaqueDataType, String> {

    public OpaqueDataTypeXmlSerializer() {
        super(OpaqueDataType::getDescription);
    }
}