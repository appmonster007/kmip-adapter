package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmJsonSerializer() {
        super(DrbgAlgorithm::getDescription);
    }
}