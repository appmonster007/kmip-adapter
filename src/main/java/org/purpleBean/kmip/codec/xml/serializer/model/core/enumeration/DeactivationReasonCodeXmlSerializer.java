package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeXmlSerializer() {
        super(DeactivationReasonCode::getDescription);
    }
}