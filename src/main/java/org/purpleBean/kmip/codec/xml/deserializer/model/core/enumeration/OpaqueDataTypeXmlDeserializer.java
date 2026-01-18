package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

public class OpaqueDataTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<OpaqueDataType, String> {

    public OpaqueDataTypeXmlDeserializer() {
        super(OpaqueDataType.kmipTag, OpaqueDataType.encodingType, String.class, value -> new OpaqueDataType(OpaqueDataType.fromName(value)));
    }
}