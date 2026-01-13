package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OtpAlgorithm, String> {

    public OtpAlgorithmJsonSerializer() {
        super(OtpAlgorithm::getDescription);
    }
}