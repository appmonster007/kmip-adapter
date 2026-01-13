package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttestationType, String> {

    public AttestationTypeJsonSerializer() {
        super(AttestationType::getDescription);
    }
}