package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidationType;

public class ValidationTypeXmlDeserializer extends AbstractKmipXmlDeserializer<ValidationType, String> {

    public ValidationTypeXmlDeserializer() {
        super(ValidationType.kmipTag, ValidationType.encodingType, String.class, value -> new ValidationType(ValidationType.fromName(value)));
    }
}