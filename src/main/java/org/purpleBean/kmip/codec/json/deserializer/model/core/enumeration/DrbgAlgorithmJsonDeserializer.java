package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmJsonDeserializer() {
        super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType, String.class, value -> DrbgAlgorithm.fromName(value).inst());
    }
}