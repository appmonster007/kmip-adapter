package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.AttestationType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AttestationTypeXmlSerializer extends AbstractKmipXmlSerializer<AttestationType, String> {

    public AttestationTypeXmlSerializer() {
        super(AttestationType::getDescription);
    }
}