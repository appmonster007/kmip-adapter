package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;

public class DerivationMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DerivationMethod, String> {

    public DerivationMethodXmlDeserializer() {
        super(DerivationMethod.kmipTag, DerivationMethod.encodingType, String.class, value -> new DerivationMethod(DerivationMethod.fromName(value)));
    }
}