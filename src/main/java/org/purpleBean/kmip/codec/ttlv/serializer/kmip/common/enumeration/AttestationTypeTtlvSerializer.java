package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttestationType, Integer> {

    public AttestationTypeTtlvSerializer() {
        super(AttestationType::getValue);
    }
}