package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class DeactivationReasonCodeXmlSerializer extends AbstractKmipXmlSerializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeXmlSerializer() {
        super(DeactivationReasonCode::getDescription);
    }
}