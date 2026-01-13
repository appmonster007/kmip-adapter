package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<InteropFunction, String> {

    public InteropFunctionJsonSerializer() {
        super(InteropFunction::getDescription);
    }
}