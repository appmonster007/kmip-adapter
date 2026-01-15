package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

public class AttestationTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttestationType, String> {

    public AttestationTypeXmlDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType, String.class, value -> new AttestationType(AttestationType.fromName(value)));
    }
}