package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ValidationType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ValidationTypeXmlSerializer extends AbstractKmipXmlSerializer<ValidationType, String> {

    public ValidationTypeXmlSerializer() {
        super(ValidationType::getDescription);
    }
}