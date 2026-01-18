package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;

public class ValidationTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ValidationType, String> {

    public ValidationTypeJsonSerializer() {
        super(ValidationType::getDescription);
    }
}