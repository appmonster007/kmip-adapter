package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.NameType;

public class NameTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NameType, String> {

    public NameTypeJsonSerializer() {
        super(NameType::getDescription);
    }
}