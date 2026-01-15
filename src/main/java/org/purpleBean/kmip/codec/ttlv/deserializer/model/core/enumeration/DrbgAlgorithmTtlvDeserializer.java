package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DrbgAlgorithm, Integer> {

    public DrbgAlgorithmTtlvDeserializer() {
        super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType, Integer.class, value -> new DrbgAlgorithm(DrbgAlgorithm.fromValue(value)));
    }
}