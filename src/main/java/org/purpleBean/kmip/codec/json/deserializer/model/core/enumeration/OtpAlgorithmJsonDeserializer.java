package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;

public class OtpAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OtpAlgorithm, String> {

    public OtpAlgorithmJsonDeserializer() {
        super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType, String.class, value -> OtpAlgorithm.fromName(value).inst());
    }
}