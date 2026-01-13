package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RevocationReasonCode, String> {

    public RevocationReasonCodeXmlSerializer() {
        super(RevocationReasonCode::getDescription);
    }
}