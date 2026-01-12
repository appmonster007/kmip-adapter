package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodTtlvDeserializer extends AbstractKmipTtlvDeserializer<DerivationMethod, Integer> {

    public DerivationMethodTtlvDeserializer() {
        super(DerivationMethod.kmipTag, DerivationMethod.encodingType, Integer.class, value -> new DerivationMethod(DerivationMethod.fromValue(value)));
    }
}