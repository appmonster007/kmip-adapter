package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ValidationType;

public class ValidationTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ValidationType, String> {

    public ValidationTypeJsonSerializer() {
        super(ValidationType::getDescription);
    }
}