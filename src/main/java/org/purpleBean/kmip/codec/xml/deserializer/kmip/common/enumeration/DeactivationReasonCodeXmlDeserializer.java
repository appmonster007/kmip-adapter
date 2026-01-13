package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeXmlDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType, String.class, value -> new DeactivationReasonCode(DeactivationReasonCode.fromName(value)));
    }
}