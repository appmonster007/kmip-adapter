package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodJsonDeserializer extends AbstractKmipJsonDeserializer<DerivationMethod, String> {

    public DerivationMethodJsonDeserializer() {
        super(DerivationMethod.kmipTag, DerivationMethod.encodingType, String.class, value -> new DerivationMethod(DerivationMethod.fromName(value)));
    }
}