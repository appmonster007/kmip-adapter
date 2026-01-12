package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeTtlvDeserializer extends AbstractKmipTtlvDeserializer<DeactivationReasonCode, Integer> {

    public DeactivationReasonCodeTtlvDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType, Integer.class, value -> new DeactivationReasonCode(DeactivationReasonCode.fromValue(value)));
    }
}