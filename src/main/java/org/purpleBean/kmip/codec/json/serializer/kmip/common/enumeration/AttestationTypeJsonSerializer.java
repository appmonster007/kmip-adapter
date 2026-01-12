package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeJsonSerializer extends AbstractKmipJsonSerializer<AttestationType, String> {

    public AttestationTypeJsonSerializer() {
        super(AttestationType::getDescription);
    }
}