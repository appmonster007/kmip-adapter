package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeJsonSerializer() {
        super(DeactivationReasonCode::getDescription);
    }
}