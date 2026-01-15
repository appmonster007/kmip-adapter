package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttestationType TTLV Serialization")
class AttestationTypeTtlvTest extends AbstractTtlvSerializationTestSuite<AttestationType> {
    @Override
    protected Class<AttestationType> type() {
        return AttestationType.class;
    }

    @Override
    protected AttestationType createDefault() {
        return AttestationType.Standard.TPM_QUOTE.inst();
    }

    @Override
    protected AttestationType createVariant() {
        return AttestationType.Standard.TCG_INTEGRITY_REPORT.inst();
    }
}
