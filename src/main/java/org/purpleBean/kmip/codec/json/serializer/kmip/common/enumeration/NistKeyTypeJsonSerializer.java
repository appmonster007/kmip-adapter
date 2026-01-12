package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

public class NistKeyTypeJsonSerializer extends AbstractKmipJsonSerializer<NistKeyType, String> {

    public NistKeyTypeJsonSerializer() {
        super(NistKeyType::getDescription);
    }
}