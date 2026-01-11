package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

public class OpaqueDataTypeXmlDeserializer extends AbstractKmipXmlDeserializer<OpaqueDataType, String> {

    public OpaqueDataTypeXmlDeserializer() {
        super(OpaqueDataType.kmipTag, OpaqueDataType.encodingType, String.class, value -> new OpaqueDataType(OpaqueDataType.fromName(value)));
    }
}