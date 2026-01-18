package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;

public class AttestationCapableIndicatorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttestationCapableIndicator, Boolean> {

    public AttestationCapableIndicatorTtlvSerializer() {
        super(AttestationCapableIndicator::getValue);
    }
}