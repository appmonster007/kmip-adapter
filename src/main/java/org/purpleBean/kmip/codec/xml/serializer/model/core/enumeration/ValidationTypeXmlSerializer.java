package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;

public class ValidationTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ValidationType, String> {

    public ValidationTypeXmlSerializer() {
        super(ValidationType::getDescription);
    }
}