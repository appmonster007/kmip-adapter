package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmJsonDeserializer() {
        super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType, String.class, value -> new DrbgAlgorithm(DrbgAlgorithm.fromName(value)));
    }
}