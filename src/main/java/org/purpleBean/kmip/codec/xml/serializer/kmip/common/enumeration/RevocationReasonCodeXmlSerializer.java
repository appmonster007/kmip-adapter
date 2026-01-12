package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class RevocationReasonCodeXmlSerializer extends AbstractKmipXmlSerializer<RevocationReasonCode, String> {

    public RevocationReasonCodeXmlSerializer() {
        super(RevocationReasonCode::getDescription);
    }
}