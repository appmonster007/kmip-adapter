package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.structure.Digest;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Digest Xml Serialization Tests")
class DigestXmlTest extends AbstractXmlSerializationTestSuite<Digest> {

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
