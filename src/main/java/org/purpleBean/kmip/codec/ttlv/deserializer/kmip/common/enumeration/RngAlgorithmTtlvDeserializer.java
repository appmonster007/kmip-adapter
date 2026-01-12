package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

public class RngAlgorithmTtlvDeserializer extends AbstractKmipTtlvDeserializer<RngAlgorithm, Integer> {

    public RngAlgorithmTtlvDeserializer() {
        super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType, Integer.class, value -> new RngAlgorithm(RngAlgorithm.fromValue(value)));
    }
}