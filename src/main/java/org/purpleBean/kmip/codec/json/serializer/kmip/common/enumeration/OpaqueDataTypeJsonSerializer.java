package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

public class OpaqueDataTypeJsonSerializer extends AbstractKmipJsonSerializer<OpaqueDataType, String> {

    public OpaqueDataTypeJsonSerializer() {
        super(OpaqueDataType::getDescription);
    }
}