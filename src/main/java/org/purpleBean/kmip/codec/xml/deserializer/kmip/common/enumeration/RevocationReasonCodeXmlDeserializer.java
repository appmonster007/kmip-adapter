package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeXmlDeserializer extends AbstractKmipXmlDeserializer<RevocationReasonCode, String> {

    public RevocationReasonCodeXmlDeserializer() {
        super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType, String.class, value -> new RevocationReasonCode(RevocationReasonCode.fromName(value)));
    }
}