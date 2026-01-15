package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;

public class ValidityIndicatorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ValidityIndicator, String> {

    public ValidityIndicatorJsonSerializer() {
        super(ValidityIndicator::getDescription);
    }
}