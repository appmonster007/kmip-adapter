package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ValidityIndicator, String> {

    public ValidityIndicatorJsonSerializer() {
        super(ValidityIndicator::getDescription);
    }
}