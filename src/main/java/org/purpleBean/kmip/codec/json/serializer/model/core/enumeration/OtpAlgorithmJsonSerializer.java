package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;

public class OtpAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OtpAlgorithm, String> {

    public OtpAlgorithmJsonSerializer() {
        super(OtpAlgorithm::getDescription);
    }
}