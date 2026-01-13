package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.AttestationType;

public class AttestationTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttestationType, String> {

    public AttestationTypeXmlSerializer() {
        super(AttestationType::getDescription);
    }
}