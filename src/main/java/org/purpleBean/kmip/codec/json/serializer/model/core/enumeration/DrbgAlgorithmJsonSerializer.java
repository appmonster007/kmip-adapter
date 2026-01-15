package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmJsonSerializer() {
        super(DrbgAlgorithm::getDescription);
    }
}