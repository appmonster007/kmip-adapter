package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DerivationMethod, Integer> {

    public DerivationMethodTtlvDeserializer() {
        super(DerivationMethod.kmipTag, DerivationMethod.encodingType, Integer.class, value -> new DerivationMethod(DerivationMethod.fromValue(value)));
    }
}