package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeJsonDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType, String.class, value -> DeactivationReasonCode.fromName(value).inst());
    }
}