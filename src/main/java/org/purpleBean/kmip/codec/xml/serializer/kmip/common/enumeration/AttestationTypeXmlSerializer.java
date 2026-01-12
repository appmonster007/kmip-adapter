package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeXmlSerializer extends AbstractKmipXmlSerializer<AttestationType, String> {

    public AttestationTypeXmlSerializer() {
        super(AttestationType::getDescription);
    }
}