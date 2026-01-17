package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;

public class AttestationCapableIndicatorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttestationCapableIndicator, Boolean> {

    public AttestationCapableIndicatorJsonSerializer() {
        super(AttestationCapableIndicator::getValue);
    }
}