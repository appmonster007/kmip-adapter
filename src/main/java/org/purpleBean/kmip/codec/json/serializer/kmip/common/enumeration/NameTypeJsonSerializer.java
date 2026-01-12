package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeJsonSerializer extends AbstractKmipJsonSerializer<NameType, String> {

    public NameTypeJsonSerializer() {
        super(NameType::getDescription);
    }
}