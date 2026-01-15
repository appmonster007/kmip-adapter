package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeJsonSerializer() {
        super(DeactivationReasonCode::getDescription);
    }
}