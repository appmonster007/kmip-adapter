package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CancellationResult JSON Serialization")
class CancellationResultJsonTest extends AbstractJsonSerializationTestSuite<CancellationResult> {
    @Override
    protected Class<CancellationResult> type() {
        return CancellationResult.class;
    }

    @Override
    protected CancellationResult createDefault() {
        return CancellationResult.Standard.CANCELED.inst();
    }

    @Override
    protected CancellationResult createVariant() {
        return CancellationResult.Standard.UNABLE_TO_CANCEL.inst();
    }
}
