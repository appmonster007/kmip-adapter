package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OtpAlgorithm, String> {

    public OtpAlgorithmJsonDeserializer() {
        super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType, String.class, value -> new OtpAlgorithm(OtpAlgorithm.fromName(value)));
    }
}