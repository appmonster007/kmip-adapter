package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CancellationResult;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("CancellationResult JSON Serialization")
class CancellationResultJsonTest extends AbstractJsonSerializationSuite<CancellationResult> {
    @Override
    protected Class<CancellationResult> type() {
        return CancellationResult.class;
    }

    @Override
    protected CancellationResult createDefault() {
        return new CancellationResult(CancellationResult.Standard.CANCELED);
    }

    @Override
    protected CancellationResult createVariant() {
        return new CancellationResult(CancellationResult.Standard.UNABLE_TO_CANCEL);
    }
}
