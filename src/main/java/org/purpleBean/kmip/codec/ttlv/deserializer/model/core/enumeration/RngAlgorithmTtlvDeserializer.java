package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RngAlgorithm, Integer> {

    public RngAlgorithmTtlvDeserializer() {
        super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType, Integer.class, value -> new RngAlgorithm(RngAlgorithm.fromValue(value)));
    }
}