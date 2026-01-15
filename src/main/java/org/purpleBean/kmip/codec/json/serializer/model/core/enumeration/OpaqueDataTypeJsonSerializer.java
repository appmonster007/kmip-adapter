package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

public class OpaqueDataTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OpaqueDataType, String> {

    public OpaqueDataTypeJsonSerializer() {
        super(OpaqueDataType::getDescription);
    }
}