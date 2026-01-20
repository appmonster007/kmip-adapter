package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeactivationReasonCode, Integer> {

    public DeactivationReasonCodeTtlvDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType, Integer.class, value -> DeactivationReasonCode.fromValue(value).inst());
    }
}