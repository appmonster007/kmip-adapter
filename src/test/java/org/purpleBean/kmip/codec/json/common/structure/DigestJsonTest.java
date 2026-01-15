package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.structure.Digest;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Digest Json Serialization Tests")
class DigestJsonTest extends AbstractJsonSerializationTestSuite<Digest> {

    @Override
    protected Class<Digest> type() {
        return Digest.class;
    }

    @Override
    protected Digest createDefault() {
        return Digest.builder()
                .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
                .digestValue(DigestValue.of(new byte[0]))
                .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
                .build();
    }
}
