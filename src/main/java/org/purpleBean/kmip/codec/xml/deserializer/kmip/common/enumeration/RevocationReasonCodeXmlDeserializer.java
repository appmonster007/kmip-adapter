package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RevocationReasonCode, String> {

    public RevocationReasonCodeXmlDeserializer() {
        super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType, String.class, value -> new RevocationReasonCode(RevocationReasonCode.fromName(value)));
    }
}