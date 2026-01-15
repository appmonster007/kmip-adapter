package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

public class AttestationTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttestationType, String> {

    public AttestationTypeJsonSerializer() {
        super(AttestationType::getDescription);
    }
}