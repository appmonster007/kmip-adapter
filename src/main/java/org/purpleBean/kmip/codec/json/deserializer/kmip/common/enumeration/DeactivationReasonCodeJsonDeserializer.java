package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeJsonDeserializer extends AbstractKmipJsonDeserializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeJsonDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType, String.class, value -> new DeactivationReasonCode(DeactivationReasonCode.fromName(value)));
    }
}