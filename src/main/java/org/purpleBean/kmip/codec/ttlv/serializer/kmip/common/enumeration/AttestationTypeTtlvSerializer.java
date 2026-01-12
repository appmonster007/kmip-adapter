package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeTtlvSerializer extends AbstractKmipTtlvSerializer<AttestationType, Integer> {

    public AttestationTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}