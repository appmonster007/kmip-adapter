package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

public class AttestationTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttestationType, Integer> {

    public AttestationTypeTtlvSerializer() {
        super(AttestationType::getIntValue);
    }
}