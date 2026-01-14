package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

public class PaddingMethodTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PaddingMethod, Integer> {

    public PaddingMethodTtlvSerializer() {
        super(PaddingMethod::getValue);
    }
}