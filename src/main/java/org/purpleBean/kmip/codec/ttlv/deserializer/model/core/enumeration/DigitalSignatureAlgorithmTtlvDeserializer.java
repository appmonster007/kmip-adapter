package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DigitalSignatureAlgorithm, Integer> {

    public DigitalSignatureAlgorithmTtlvDeserializer() {
        super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType, Integer.class, value -> DigitalSignatureAlgorithm.fromValue(value).inst());
    }
}