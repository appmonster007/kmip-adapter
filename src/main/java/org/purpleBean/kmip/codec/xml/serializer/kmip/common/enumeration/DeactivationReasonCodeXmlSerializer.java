package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DeactivationReasonCode, String> {

    public DeactivationReasonCodeXmlSerializer() {
        super(DeactivationReasonCode::getDescription);
    }
}