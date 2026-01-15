package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;

public class PaddingMethodTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PaddingMethod, Integer> {

    public PaddingMethodTtlvSerializer() {
        super(PaddingMethod::getValue);
    }
}