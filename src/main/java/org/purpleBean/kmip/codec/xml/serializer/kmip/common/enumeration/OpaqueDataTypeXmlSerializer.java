package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

public class OpaqueDataTypeXmlSerializer extends AbstractKmipXmlSerializer<OpaqueDataType, String> {

    public OpaqueDataTypeXmlSerializer() {
        super(OpaqueDataType::getDescription);
    }
}