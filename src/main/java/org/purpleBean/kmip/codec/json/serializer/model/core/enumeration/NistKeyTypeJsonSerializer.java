package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;

public class NistKeyTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NistKeyType, String> {

    public NistKeyTypeJsonSerializer() {
        super(NistKeyType::getDescription);
    }
}