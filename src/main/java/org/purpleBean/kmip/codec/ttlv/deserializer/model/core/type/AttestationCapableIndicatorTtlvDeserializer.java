package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;

public class AttestationCapableIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttestationCapableIndicator, Boolean> {

    public AttestationCapableIndicatorTtlvDeserializer() {
        super(AttestationCapableIndicator.kmipTag, AttestationCapableIndicator.encodingType, Boolean.class, value -> AttestationCapableIndicator.builder().value(value).build());
    }
}