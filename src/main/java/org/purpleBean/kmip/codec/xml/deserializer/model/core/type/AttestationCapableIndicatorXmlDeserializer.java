package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;

public class AttestationCapableIndicatorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttestationCapableIndicator, Boolean> {

    public AttestationCapableIndicatorXmlDeserializer() {
        super(AttestationCapableIndicator.kmipTag, AttestationCapableIndicator.encodingType, Boolean.class, value -> AttestationCapableIndicator.builder().value(value).build());
    }
}