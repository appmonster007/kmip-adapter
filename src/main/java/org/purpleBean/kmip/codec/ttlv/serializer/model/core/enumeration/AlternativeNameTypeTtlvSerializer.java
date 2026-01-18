package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AlternativeNameType, Integer> {

    public AlternativeNameTypeTtlvSerializer() {
        super(AlternativeNameType::getValue);
    }
}