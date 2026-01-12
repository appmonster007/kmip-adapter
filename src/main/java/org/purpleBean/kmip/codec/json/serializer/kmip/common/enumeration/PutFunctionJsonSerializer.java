package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

public class PutFunctionJsonSerializer extends AbstractKmipJsonSerializer<PutFunction, String> {

    public PutFunctionJsonSerializer() {
        super(PutFunction::getDescription);
    }
}