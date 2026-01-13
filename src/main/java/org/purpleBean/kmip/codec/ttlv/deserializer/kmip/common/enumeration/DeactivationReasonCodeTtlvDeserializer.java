package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeactivationReasonCode, Integer> {

    public DeactivationReasonCodeTtlvDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType, Integer.class, value -> new DeactivationReasonCode(DeactivationReasonCode.fromValue(value)));
    }
}