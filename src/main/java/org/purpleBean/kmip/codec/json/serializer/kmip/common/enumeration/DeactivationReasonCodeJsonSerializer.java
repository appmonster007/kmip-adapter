package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeJsonSerializer extends AbstractKmipJsonSerializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeJsonSerializer() {
        super(DeactivationReasonCode::getDescription);
    }
}