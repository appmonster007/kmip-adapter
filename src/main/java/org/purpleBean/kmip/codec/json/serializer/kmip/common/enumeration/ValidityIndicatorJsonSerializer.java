package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorJsonSerializer extends AbstractKmipJsonSerializer<ValidityIndicator, String> {

    public ValidityIndicatorJsonSerializer() {
        super(ValidityIndicator::getDescription);
    }
}