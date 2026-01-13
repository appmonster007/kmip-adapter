package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

public class OpaqueDataTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OpaqueDataType, String> {

    public OpaqueDataTypeJsonSerializer() {
        super(OpaqueDataType::getDescription);
    }
}