package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;

public class RevocationReasonCodeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RevocationReasonCode, String> {

    public RevocationReasonCodeXmlDeserializer() {
        super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType, String.class, value -> RevocationReasonCode.fromName(value).inst());
    }
}