package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

public class AttestationTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttestationType, Integer> {

    public AttestationTypeTtlvDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType, Integer.class, value -> AttestationType.fromValue(value).inst());
    }
}