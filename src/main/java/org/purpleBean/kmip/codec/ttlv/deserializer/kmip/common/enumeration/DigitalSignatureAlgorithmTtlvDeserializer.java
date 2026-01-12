package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmTtlvDeserializer extends AbstractKmipTtlvDeserializer<DigitalSignatureAlgorithm, Integer> {

    public DigitalSignatureAlgorithmTtlvDeserializer() {
        super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType, Integer.class, value -> new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.fromValue(value)));
    }
}