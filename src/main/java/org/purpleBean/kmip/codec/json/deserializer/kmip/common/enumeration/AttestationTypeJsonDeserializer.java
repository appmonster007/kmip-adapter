package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeJsonDeserializer extends AbstractKmipJsonDeserializer<AttestationType, String> {

    public AttestationTypeJsonDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType, String.class, value -> new AttestationType(AttestationType.fromName(value)));
    }
}