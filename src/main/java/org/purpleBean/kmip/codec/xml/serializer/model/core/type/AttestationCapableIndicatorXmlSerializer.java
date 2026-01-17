package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;

public class AttestationCapableIndicatorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttestationCapableIndicator, Boolean> {

    public AttestationCapableIndicatorXmlSerializer() {
        super(AttestationCapableIndicator::getValue);
    }
}