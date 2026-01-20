package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeXmlDeserializer() {
        super(DeactivationReasonCode.kmipTag, DeactivationReasonCode.encodingType, String.class, value -> DeactivationReasonCode.fromName(value).inst());
    }
}