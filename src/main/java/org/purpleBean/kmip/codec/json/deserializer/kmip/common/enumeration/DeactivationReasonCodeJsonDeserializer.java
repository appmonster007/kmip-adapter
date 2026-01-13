package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeJsonDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType, String.class, value -> new DeactivationReasonCode(DeactivationReasonCode.fromName(value)));
    }
}