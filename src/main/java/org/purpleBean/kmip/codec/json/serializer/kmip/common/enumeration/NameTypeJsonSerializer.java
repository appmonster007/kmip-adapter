package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NameType, String> {

    public NameTypeJsonSerializer() {
        super(NameType::getDescription);
    }
}