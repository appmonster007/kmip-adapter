package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;

public class AttestationCapableIndicatorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttestationCapableIndicator, Boolean> {

    public AttestationCapableIndicatorJsonDeserializer() {
        super(AttestationCapableIndicator.kmipTag, AttestationCapableIndicator.encodingType, Boolean.class, value -> AttestationCapableIndicator.builder().value(value).build());
    }
}