package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;

public class DerivationMethodJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DerivationMethod, String> {

    public DerivationMethodJsonDeserializer() {
        super(DerivationMethod.kmipTag, DerivationMethod.encodingType, String.class, value -> new DerivationMethod(DerivationMethod.fromName(value)));
    }
}