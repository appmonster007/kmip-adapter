package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmTtlvDeserializer extends AbstractKmipTtlvDeserializer<DrbgAlgorithm, Integer> {

    public DrbgAlgorithmTtlvDeserializer() {
        super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType, Integer.class, value -> new DrbgAlgorithm(DrbgAlgorithm.fromValue(value)));
    }
}