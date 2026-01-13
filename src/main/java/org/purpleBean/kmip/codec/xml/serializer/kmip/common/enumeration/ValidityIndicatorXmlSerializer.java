package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ValidityIndicator, String> {

    public ValidityIndicatorXmlSerializer() {
        super(ValidityIndicator::getDescription);
    }
}