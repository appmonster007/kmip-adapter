package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;

public class PutFunctionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PutFunction, String> {

    public PutFunctionJsonSerializer() {
        super(PutFunction::getDescription);
    }
}