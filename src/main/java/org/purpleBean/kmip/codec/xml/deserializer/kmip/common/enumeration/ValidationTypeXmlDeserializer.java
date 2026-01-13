package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidationType;

public class ValidationTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidationType, String> {

    public ValidationTypeXmlDeserializer() {
        super(ValidationType.kmipTag, ValidationType.encodingType, String.class, value -> new ValidationType(ValidationType.fromName(value)));
    }
}