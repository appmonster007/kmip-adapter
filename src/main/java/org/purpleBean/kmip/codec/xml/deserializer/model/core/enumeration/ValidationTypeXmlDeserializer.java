package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;

public class ValidationTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidationType, String> {

    public ValidationTypeXmlDeserializer() {
        super(ValidationType.kmipTag, ValidationType.encodingType, String.class, value -> new ValidationType(ValidationType.fromName(value)));
    }
}