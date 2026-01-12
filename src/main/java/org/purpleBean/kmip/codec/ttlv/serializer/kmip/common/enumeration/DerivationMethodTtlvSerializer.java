package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodTtlvSerializer extends AbstractKmipTtlvSerializer<DerivationMethod, Integer> {

    public DerivationMethodTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}