package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmJsonDeserializer extends AbstractKmipJsonDeserializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmJsonDeserializer() {
        super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType, String.class, value -> new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.fromName(value)));
    }
}