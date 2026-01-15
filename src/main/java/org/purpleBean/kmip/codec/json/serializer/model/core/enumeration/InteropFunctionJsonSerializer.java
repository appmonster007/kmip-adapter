package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.InteropFunction;

public class InteropFunctionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<InteropFunction, String> {

    public InteropFunctionJsonSerializer() {
        super(InteropFunction::getDescription);
    }
}