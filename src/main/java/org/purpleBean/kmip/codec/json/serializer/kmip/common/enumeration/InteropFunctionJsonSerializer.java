package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

public class InteropFunctionJsonSerializer extends AbstractKmipJsonSerializer<InteropFunction, String> {

    public InteropFunctionJsonSerializer() {
        super(InteropFunction::getDescription);
    }
}