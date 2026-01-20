package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

public class AttestationTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttestationType, String> {

    public AttestationTypeJsonDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType, String.class, value -> AttestationType.fromName(value).inst());
    }
}