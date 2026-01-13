package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

public class AlternativeNameTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AlternativeNameType, String> {

    public AlternativeNameTypeJsonSerializer() {
        super(AlternativeNameType::getDescription);
    }
}