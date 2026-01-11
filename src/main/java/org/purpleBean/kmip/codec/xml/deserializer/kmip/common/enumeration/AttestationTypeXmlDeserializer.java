package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeXmlDeserializer extends AbstractKmipXmlDeserializer<AttestationType, String> {

    public AttestationTypeXmlDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType, String.class, value -> new AttestationType(AttestationType.fromName(value)));
    }
}