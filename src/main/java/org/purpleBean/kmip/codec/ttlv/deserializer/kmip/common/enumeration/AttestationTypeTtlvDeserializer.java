package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttestationType, Integer> {

    public AttestationTypeTtlvDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType, Integer.class, value -> new AttestationType(AttestationType.fromValue(value)));
    }
}