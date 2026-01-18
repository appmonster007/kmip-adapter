package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AlternativeNameType, String> {

    public AlternativeNameTypeJsonSerializer() {
        super(AlternativeNameType::getDescription);
    }
}