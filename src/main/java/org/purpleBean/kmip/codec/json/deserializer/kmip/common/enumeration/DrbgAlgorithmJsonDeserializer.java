package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmJsonDeserializer extends AbstractKmipJsonDeserializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmJsonDeserializer() {
        super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType, String.class, value -> new DrbgAlgorithm(DrbgAlgorithm.fromName(value)));
    }
}