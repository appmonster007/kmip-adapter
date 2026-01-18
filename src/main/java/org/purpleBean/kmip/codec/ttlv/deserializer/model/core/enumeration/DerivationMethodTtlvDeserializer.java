package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;

public class DerivationMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DerivationMethod, Integer> {

    public DerivationMethodTtlvDeserializer() {
        super(DerivationMethod.kmipTag, DerivationMethod.encodingType, Integer.class, value -> new DerivationMethod(DerivationMethod.fromValue(value)));
    }
}