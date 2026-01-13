package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

public class NistKeyTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NistKeyType, String> {

    public NistKeyTypeJsonSerializer() {
        super(NistKeyType::getDescription);
    }
}