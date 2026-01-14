package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DerivationMethod, Integer> {

    public DerivationMethodTtlvSerializer() {
        super(DerivationMethod::getValue);
    }
}